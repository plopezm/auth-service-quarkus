package com.aeox.auth.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder(builderMethodName = "entityBuilder")
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class AbstractEntity extends PanacheEntityBase {
    @Id
    private UUID id;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

    @Version
    private long version;

    public UUID getId() {
        return id;
    }

    @JsonbTransient
    public void setId(UUID id) {
        this.id = id;
    }

    @JsonbDateFormat("dd/MM/yyyy HH:mm:SS")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonbTransient
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @JsonbDateFormat("dd/MM/yyyy HH:mm:SS")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @JsonbTransient
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getVersion() {
        return version;
    }

    @JsonbTransient
    public void setVersion(long version) {
        this.version = version;
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
