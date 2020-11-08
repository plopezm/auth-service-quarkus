package com.aeox.auth.entity;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(
    name = "roles",    
    indexes = {
        @Index(name = "roles_name_index", columnList = "name", unique = true)
    }
)
public class Role extends AbstractEntity {
    @Column(unique = true)
    private String name;

    private String description;

    public Role() {}

    public Role(final String name, final String description) {
        this.name = name;
        this.description = description;
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

	public static Optional<Role> findByName(String name) {
		final Role result = find("name = ?1", name).firstResult();
        return Optional.ofNullable(result);
	}
}
