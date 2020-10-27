package com.aeox.auth.repository;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.aeox.auth.entity.User;

@ApplicationScoped
public class UserRepositoryPanacheImpl implements UserRepository {

    @Override
    public Optional<User> findByUsername(String username) {
        return User.findByUsername(username);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return User.findByUsernameAndPassword(username, password);
    }    
}
