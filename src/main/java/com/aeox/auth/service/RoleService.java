package com.aeox.auth.service;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.aeox.auth.entity.Role;
import com.aeox.auth.exception.EntityNotFoundException;
import com.aeox.auth.repository.RoleRepository;

@ApplicationScoped
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }    

    public Role getByName(final String name) {
        return this.roleRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException(Role.class));
    }

    @Transactional
	public Role create(final Role role) {
        this.roleRepository.persist(role);
		return role;
	}
    
}
