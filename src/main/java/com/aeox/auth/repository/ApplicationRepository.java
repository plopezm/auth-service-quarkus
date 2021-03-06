package com.aeox.auth.repository;

import java.util.Optional;
import java.util.UUID;

import com.aeox.auth.entity.Application;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface ApplicationRepository extends PanacheRepositoryBase<Application, UUID> {
    Optional<Application> findByName(String name);
}
