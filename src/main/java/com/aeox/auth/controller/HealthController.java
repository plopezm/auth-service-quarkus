package com.aeox.auth.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;


@Tag(name = "Health", description = "Health features")
@Path("/status")
public class HealthController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String status() {
        return "OK";
    }
    
}