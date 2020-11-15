package com.aeox.auth.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.aeox.auth.dto.role.CreateRoleRequest;
import com.aeox.auth.entity.Role;
import com.aeox.auth.exception.EntityNotFoundException;
import com.aeox.auth.repository.RoleRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class RoleService {

    private RoleRepository roleRepository;

    public Role getByName(final String name) {
        return this.roleRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException(Role.class));
    }

	public List<Role> getAll() {
		return this.roleRepository.findAll().list();
	}

    @Transactional
	public Role create(final CreateRoleRequest req) {
        final Role role = Role.builder()
            .name(req.getName())
            .description(req.getDesc())
            .build(); 
        this.roleRepository.persist(role);
		return role;
	}
    
}
