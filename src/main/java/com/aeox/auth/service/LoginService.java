package com.aeox.auth.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.enterprise.context.ApplicationScoped;

import com.aeox.auth.dto.LoginRequest;
import com.aeox.auth.dto.LoginResponse;
import com.aeox.auth.entity.User;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class LoginService {
    private UserService userService;

    public LoginService(final UserService userService) {
        this.userService = userService;
    }

    public LoginResponse login(final LoginRequest loginRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        final User user = this.userService.getByUsernameAndPassword(loginRequest.username, loginRequest.password);
        return new LoginResponse(this.generateToken(user));
    }
    
    public LoginResponse signup(final User user) {
        final User userRegistered = this.userService.create(user);
        return new LoginResponse(this.generateToken(userRegistered));
    }

    private String generateToken(final User user) {
        return Jwt.issuer("https://auth-service.aeox.com") 
        .upn(user.getId().toString())
        //.groups(new HashSet()<>(Arrays.asList("User", "Admin"))) 
        .claim(Claims.nickname.name(), user.username) 
        .sign();
    }
}
