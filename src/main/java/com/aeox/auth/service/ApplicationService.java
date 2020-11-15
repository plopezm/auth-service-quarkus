package com.aeox.auth.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.aeox.auth.dto.application.CreateApplicationRequest;
import com.aeox.auth.entity.Application;
import com.aeox.auth.exception.EntityNotFoundException;
import com.aeox.auth.repository.ApplicationRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class ApplicationService {
    
    private ApplicationRepository applicationRepository;
    private RoleService roleService;

	public List<Application> getAll() {
		return this.applicationRepository.findAll().list();
	}

    public Application getByName(final String name) {
        return this.applicationRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException(Application.class));
    }

    @Transactional
    public Application create(final CreateApplicationRequest req) {
        final Application app = new Application(req.getName());
        app.setDescription(req.getDescription());
        app.setRoles(req.getRoles().stream().map((roleName) -> {
            return this.roleService.getByName(roleName);
        }).collect(Collectors.toList()));
        applicationRepository.persist(app);
        return app;
    }

}
