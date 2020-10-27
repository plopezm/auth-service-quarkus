package com.aeox.auth.entity;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {
    @NotBlank
    @Column(unique = true)
    public String username;
    @NotBlank
    public String password;
    @JsonbTransient
    private byte[] salt;
    public String fullName;

    @JsonbTransient
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    @PrePersist
    public void onUserPersist() throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.generateSalt();
        this.password = hashPasswordUsingSalt(this.password, this.salt);
    }

    public static String hashPasswordUsingSalt(final String password, final byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return Base64.getEncoder().encodeToString(factory.generateSecret(spec).getEncoded());
    }

    private void generateSalt() {
        SecureRandom random = new SecureRandom();
        this.salt = new byte[16];
        random.nextBytes(this.salt);
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
