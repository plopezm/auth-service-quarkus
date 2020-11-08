package com.aeox.auth.repository;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.aeox.auth.entity.Application;

@ApplicationScoped
public class ApplicationRepositoryPanacheImpl implements ApplicationRepository {

    @Override
    public Optional<Application> findByName(String name) {
        return Application.findByName(name);
    }
    
}
