package com.aeox.auth.service;

import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.aeox.auth.dto.signup.CreateScopeRequest;
import com.aeox.auth.entity.Application;
import com.aeox.auth.entity.Role;
import com.aeox.auth.entity.Scope;
import com.aeox.auth.entity.User;
import com.aeox.auth.repository.ScopeRepository;

@ApplicationScoped
public class ScopeService {
    private ScopeRepository scopeRepository;
    private ApplicationService applicationService;
    private RoleService roleService;

    public ScopeService(ScopeRepository scopeRepository, ApplicationService applicationService,
            RoleService roleService) {
        this.scopeRepository = scopeRepository;
        this.applicationService = applicationService;
        this.roleService = roleService;
    }
    
    @Transactional
    public Scope create(final User user, final CreateScopeRequest request) {
        final Application app = this.applicationService.getByName(request.getApplicationName());
        final List<Role> roles = new LinkedList<>();
        request.getRoles().forEach(role -> {
            roles.add(roleService.getByName(role));
        });

        final Scope scope = new Scope(user, app, roles);
        this.scopeRepository.persist(scope);
        return scope;
    }

}
