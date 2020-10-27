package com.aeox.auth.controller;

import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aeox.auth.entity.User;
import com.aeox.auth.service.UserService;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.security.Authenticated;

@Authenticated
@Path("/api/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class UserController {
    private UserService userService;
    
    @Inject
    private JsonWebToken jwt;

    public UserController(final UserService userService) {
        this.userService = userService;
    }
    
    @GET
    @Path("/me")
    public User me() {
        return this.userService.getById(UUID.fromString(this.jwt.getName()));
    }

}
