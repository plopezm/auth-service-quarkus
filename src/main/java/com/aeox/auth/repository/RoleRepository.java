package com.aeox.auth.repository;

import java.util.Optional;
import java.util.UUID;

import com.aeox.auth.entity.Role;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface RoleRepository extends PanacheRepositoryBase<Role, UUID> {
    Optional<Role> findByName(String name);
}
