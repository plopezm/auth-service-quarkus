package com.aeox.auth.service;

import javax.enterprise.context.ApplicationScoped;

import com.aeox.auth.entity.Application;
import com.aeox.auth.exception.EntityNotFoundException;
import com.aeox.auth.repository.ApplicationRepository;

@ApplicationScoped
public class ApplicationService {
    
    private ApplicationRepository applicationRepository;

    public ApplicationService(final ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }    

    public Application create(final Application application) {
        applicationRepository.persist(application);
        return application;
    }

    public Application getByName(final String name) {
        return this.applicationRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException(Application.class));
    }
}
