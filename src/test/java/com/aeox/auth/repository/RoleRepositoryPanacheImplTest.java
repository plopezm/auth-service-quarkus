package com.aeox.auth.repository;

import javax.persistence.EntityManager;

import com.aeox.auth.entity.Role;
import com.google.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class RoleRepositoryPanacheImplTest {

    @Inject
    private EntityManager em;

    @Test
    public void when_role_persist_then_validation_error() {
        var role = new Role();
        em.persist(role);
    }
}
