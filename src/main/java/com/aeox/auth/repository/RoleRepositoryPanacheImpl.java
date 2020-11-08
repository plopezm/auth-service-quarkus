package com.aeox.auth.repository;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.aeox.auth.entity.Role;

@ApplicationScoped
public class RoleRepositoryPanacheImpl implements RoleRepository {
    @Override
    public Optional<Role> findByName(String name) {
        return Role.findByName(name);
    }    
}
