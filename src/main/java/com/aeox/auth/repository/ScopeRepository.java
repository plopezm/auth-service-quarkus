package com.aeox.auth.repository;

import java.util.UUID;

import com.aeox.auth.entity.Scope;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface ScopeRepository extends PanacheRepositoryBase<Scope, UUID> {
    
}
