package com.aeox.auth.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@MappedSuperclass
public class AbstractEntity extends PanacheEntityBase {
    @Id
    private UUID id;
    @JsonbDateFormat("dd/MM/yyyy")
    private LocalDateTime createdAt;
    @JsonbDateFormat("dd/MM/yyyy")
    private LocalDateTime updatedAt;
    @Version
    private long version;

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }    

    @PrePersist
    public void onAbstractEntityPersist() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onAbstractEntityUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
