package com.aeox.auth.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.aeox.auth.entity.User;
import com.aeox.auth.exception.EntityNotFoundException;
import com.aeox.auth.exception.LoginInvalidException;
import com.aeox.auth.repository.UserRepository;

@ApplicationScoped
public class UserService {
    private UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(final UUID id) {
        return this.userRepository.findByIdOptional(id).orElseThrow(() -> new EntityNotFoundException(User.class));
    }

    public User getByUsername(final String username)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
       return this.userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(User.class));
    }

    @Transactional
    public User create(final User user) {
        this.userRepository.persist(user);
        return user;
    }
}
