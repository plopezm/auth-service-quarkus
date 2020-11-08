package com.aeox.auth.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.enterprise.context.ApplicationScoped;

import com.aeox.auth.dto.login.LoginRequest;
import com.aeox.auth.dto.login.LoginResponse;
import com.aeox.auth.dto.signup.SignupRequest;
import com.aeox.auth.entity.User;
import com.aeox.auth.exception.EntityNotFoundException;
import com.aeox.auth.exception.LoginInvalidException;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class LoginService {
    private UserService userService;
    private ScopeService scopeService;

    public LoginService(final UserService userService, final ScopeService scopeService) {
        this.userService = userService;
        this.scopeService = scopeService;
    }

    public LoginResponse login(final LoginRequest loginRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = null;
        try{
            user = this.userService.getByUsername(loginRequest.username);
        } catch (EntityNotFoundException e) {
            throw new LoginInvalidException(e);
        }
        if (!user.getPassword().equals(User.hashPasswordUsingSalt(loginRequest.password, user.getSalt()))) {
            throw new LoginInvalidException();
        }
        return new LoginResponse(this.generateToken(user));
    }
    
    public LoginResponse signup(final SignupRequest request) {
        final User userRegistered = this.userService.create(request.getUserEntity());
        if (request.hasScopes()) {
            request.getScopes().forEach(scopeRequest -> {
                this.scopeService.create(userRegistered, scopeRequest);
            });
        }
        return new LoginResponse(this.generateToken(userRegistered));
    }

    private String generateToken(final User user) {
        return Jwt.issuer("https://auth-service.aeox.com") 
        .upn(user.getId().toString())
        //.groups(new HashSet()<>(Arrays.asList("User", "Admin"))) 
        .claim(Claims.nickname.name(), user.getUsername())
        .sign();
    }
}
