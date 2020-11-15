package com.aeox.auth.service;

import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.aeox.auth.dto.application.CreateApplicationRequest;
import com.aeox.auth.entity.Application;
import com.aeox.auth.exception.EntityNotFoundException;
import com.aeox.auth.repository.ApplicationRepository;

@ApplicationScoped
public class ApplicationService {
    
    private ApplicationRepository applicationRepository;
    private RoleService roleService;

    public ApplicationService(final ApplicationRepository applicationRepository, final RoleService roleService) {
        this.applicationRepository = applicationRepository;
        this.roleService = roleService;
    }    

    @Transactional
    public Application create(final CreateApplicationRequest req) {
        final Application app = new Application(req.name);
        app.setDescription(req.description);
        app.setRoles(req.roles.stream().map((roleName) -> {
            return this.roleService.getByName(roleName);
        }).collect(Collectors.toList()));
        applicationRepository.persist(app);
        return app;
    }

    public Application getByName(final String name) {
        return this.applicationRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException(Application.class));
    }
}
