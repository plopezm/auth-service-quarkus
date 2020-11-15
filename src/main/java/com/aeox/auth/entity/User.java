package com.aeox.auth.entity;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.aeox.auth.config.security.SecurityUtils;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {
    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    @JsonbTransient
    private byte[] salt;

    @NotBlank
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Scope> scopes;

    public User() {}

    public User(
        @NotBlank final String username, 
        @NotBlank final String password, 
        @NotBlank final String email
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @JsonbTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(final byte[] salt) {
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public List<Scope> getScopes() {
        return scopes;
    }

    public void setScopes(final List<Scope> scopes) {
        this.scopes = scopes;
    }

    private void generateSalt() {
        final SecureRandom random = new SecureRandom();
        this.salt = new byte[16];
        random.nextBytes(this.salt);
    }
    
    @PrePersist
    public void onUserPersist() throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.generateSalt();
        this.password = SecurityUtils.hashPasswordUsingSalt(this.password, this.salt);
    }

    public static Optional<User> findByUsername(final String username) {
        final User result = find("username", username).firstResult();
        return Optional.ofNullable(result);
    }

    public static Optional<User> findByUsernameAndPassword(final String username, final String password) {
        final User result = find("username = ?1 and password = ?2", username, password).firstResult();
        return Optional.ofNullable(result);
    }
}
