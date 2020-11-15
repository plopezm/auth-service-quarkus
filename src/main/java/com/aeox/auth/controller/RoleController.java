package com.aeox.auth.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aeox.auth.dto.role.CreateRoleRequest;
import com.aeox.auth.entity.Role;
import com.aeox.auth.service.RoleService;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Role", description = "Role features")
@Path("/api/v1/roles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoleController {
    private RoleService roleService;

    public RoleController(final RoleService roleService) {
        this.roleService = roleService;
    }

    @GET
    public List<Role> getAll() {
        return this.roleService.getAll();
    }

    @POST
    public Role create(final CreateRoleRequest request) {
        return this.roleService.create(request);
    }
}
