package com.aeox.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "applications")
public class Application extends AbstractEntity {
    @Column(unique = true)
    private String name;
    private String description;

    public Application() {}

    public Application(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
