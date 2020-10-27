package com.aeox.auth.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aeox.auth.dto.LoginRequest;
import com.aeox.auth.dto.LoginResponse;
import com.aeox.auth.entity.User;
import com.aeox.auth.service.LoginService;

@Path("/api/v1/auth")
public class LoginController {
    @Inject Validator validator;
    private LoginService loginService;

    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LoginResponse login(@Valid final LoginRequest req) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return this.loginService.login(req);
    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LoginResponse signup(@Valid final User user) {
        return this.loginService.signup(user);
    }
}
