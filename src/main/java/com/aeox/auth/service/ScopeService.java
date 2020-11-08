package com.aeox.auth.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.aeox.auth.dto.signup.CreateScopeRequest;
import com.aeox.auth.entity.Application;
import com.aeox.auth.entity.Role;
import com.aeox.auth.entity.Scope;
import com.aeox.auth.entity.User;
import com.aeox.auth.exception.ApplicationRoleNotFoundException;
import com.aeox.auth.repository.ScopeRepository;

@ApplicationScoped
public class ScopeService {
    private ScopeRepository scopeRepository;
    private ApplicationService applicationService;

    public ScopeService(ScopeRepository scopeRepository, ApplicationService applicationService) {
        this.scopeRepository = scopeRepository;
        this.applicationService = applicationService;
    }
    
    @Transactional
    public Scope create(final User user, final CreateScopeRequest request) {
        final Application app = this.applicationService.getByName(request.getApplicationName());
        final List<Role> roles = new LinkedList<>();
        request.getRoles().forEach(role -> {
            final Optional<Role> appRole = app.hasRole(role);
            if (appRole.isPresent()) {
                roles.add(appRole.get());
            } else {
                throw new ApplicationRoleNotFoundException(role, app.getName());
            }
        });
        final Scope scope = new Scope(user, app, roles);
        this.scopeRepository.persist(scope);
        return scope;
    }

}
