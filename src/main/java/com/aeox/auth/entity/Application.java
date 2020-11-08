package com.aeox.auth.entity;

import java.util.Optional;
import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    @ManyToMany
    @JoinTable(
        name = "applications_roles",
        joinColumns = @JoinColumn(name = "application_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    @JsonbTransient
    public Optional<Role> hasRole(final String roleName) {
        for (Role role : this.getRoles()){
            if (role.getName().equals(roleName)) {
                return Optional.of(role);
            }
        }
        return Optional.empty();
    }

	public static Optional<Application> findByName(final String name) {
        final Application result = find("name = ?1", name).firstResult();
        return Optional.ofNullable(result);
    }
}
