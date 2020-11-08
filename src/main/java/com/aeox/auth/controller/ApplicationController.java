package com.aeox.auth.controller;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aeox.auth.dto.application.CreateApplicationRequest;
import com.aeox.auth.entity.Application;
import com.aeox.auth.service.ApplicationService;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Application", description = "Application features")
@Path("/api/v1/applications")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApplicationController {
    private ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @POST
    public Application create(@Valid final CreateApplicationRequest req) {
        return this.applicationService.create(req);
    }
}
