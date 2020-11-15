package com.aeox.auth.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.transaction.Transactional;

import com.aeox.auth.config.AuthServiceProperties;
import com.aeox.auth.config.security.SecurityUtils;
import com.aeox.auth.dto.login.LoginRequest;
import com.aeox.auth.dto.login.LoginResponse;
import com.aeox.auth.dto.signup.SignupRequest;
import com.aeox.auth.entity.Scope;
import com.aeox.auth.entity.User;
import com.aeox.auth.exception.EntityNotFoundException;
import com.aeox.auth.exception.LoginInvalidException;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class LoginService {
    private AuthServiceProperties properties;
    private UserService userService;
    private ScopeService scopeService;

    public LoginService(final AuthServiceProperties properties, final UserService userService, final ScopeService scopeService) {
        this.properties = properties;
        this.userService = userService;
        this.scopeService = scopeService;
    }

    public LoginResponse login(final LoginRequest loginRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = null;
        try{
            user = this.userService.getByUsername(loginRequest.getUsername());
        } catch (EntityNotFoundException e) {
            throw new LoginInvalidException(e);
        }
        if (!user.getPassword().equals(SecurityUtils.hashPasswordUsingSalt(loginRequest.getPassword(), user.getSalt()))) {
            throw new LoginInvalidException();
        }
        return new LoginResponse(this.generateToken(user, user.getScopes()));
    }
    
    @Transactional
    public LoginResponse signup(final SignupRequest request) {
        final User userRegistered = this.userService.create(request.getUserEntity());
        final List<Scope> createdScopes = new LinkedList<>();
        if (request.hasScopes()) {
            request.getScopes().forEach(scopeRequest -> {
                createdScopes.add(this.scopeService.create(userRegistered, scopeRequest));
            });
        }
        return new LoginResponse(this.generateToken(userRegistered, createdScopes));
    }

    private String generateToken(final User user, final List<Scope> scopes) {
        final JsonArrayBuilder scopesBuilder = Json.createArrayBuilder();
        scopes.stream().forEach(scope -> {
            final JsonObjectBuilder scopeBuilder = Json.createObjectBuilder();
            scopeBuilder.add("app", scope.getApplication().getName());
            final JsonArrayBuilder rolesBuilder = Json.createArrayBuilder();
            scope.getRoles().forEach(role -> rolesBuilder.add(role.getName()));
            scopeBuilder.add("roles", rolesBuilder.build());
            scopesBuilder.add(scopeBuilder.build());
        });
        return Jwt.issuer(this.properties.getIssuer())
        .upn(user.getId().toString())        
        .claim(Claims.nickname.name(), user.getUsername())
        .claim("scopes", scopesBuilder.build())
        .sign();
    }
}
