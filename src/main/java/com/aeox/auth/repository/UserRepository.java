package com.aeox.auth.repository;

import java.util.Optional;
import java.util.UUID;

import com.aeox.auth.entity.User;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface UserRepository extends PanacheRepositoryBase<User, UUID> {    
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
