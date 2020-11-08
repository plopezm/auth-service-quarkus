package com.aeox.auth.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
    name = "scopes",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"user_id", "application_id"}
    )
)
public class Scope extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToMany
    @JoinTable(
        name = "scopes_roles",
        joinColumns = @JoinColumn(name = "scope_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public Scope() {}

    public Scope(final User user, final Application application, final List<Role> roles) {
        this.user = user;
        this.application = application;
        this.roles = roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(final Application application) {
        this.application = application;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }
}
