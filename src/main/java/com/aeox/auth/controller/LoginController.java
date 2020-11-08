package com.aeox.auth.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aeox.auth.dto.login.LoginRequest;
import com.aeox.auth.dto.login.LoginResponse;
import com.aeox.auth.dto.signup.SignupRequest;
import com.aeox.auth.service.LoginService;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Login", description = "Authorization features")
@Path("/api/v1/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {
    private LoginService loginService;

    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @POST
    @Path("/login")
    public LoginResponse login(@Valid final LoginRequest req) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return this.loginService.login(req);
    }

    @POST
    @Path("/signup")
    public LoginResponse signup(@Valid final SignupRequest request) {
        return this.loginService.signup(request);
    }
}
