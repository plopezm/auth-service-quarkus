package com.aeox.auth.entity;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(
    name = "applications",
    indexes = {
        @Index(name = "apps_name_index", columnList = "name", unique = true)
    }
)
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

	public static Optional<Application> findByName(String name) {
		final Application result = find("name = ?1", name).firstResult();
        return Optional.ofNullable(result);
	}
}
