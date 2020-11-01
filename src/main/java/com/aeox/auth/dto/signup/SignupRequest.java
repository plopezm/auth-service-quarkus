package com.aeox.auth.dto.signup;

import java.util.List;
import java.util.stream.Collectors;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.aeox.auth.entity.Application;
import com.aeox.auth.entity.Role;
import com.aeox.auth.entity.Scope;
import com.aeox.auth.entity.User;

public class SignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    private List<SignupScopeRequest> scopes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SignupScopeRequest> getScopes() {
        return scopes;
    }

    public void setScopes(List<SignupScopeRequest> scopes) {
        this.scopes = scopes;
    }

	public boolean hasScopes() {
		return this.scopes != null && this.scopes.size() > 0;
	}

    @JsonbTransient
	public User getUserEntity() {
		return new User(
            this.username,
            this.password,
            this.email
        );
    }
    
    @JsonbTransient
	public List<Scope> getScopesEntity(final User user) {
		return this.scopes.stream().map(scope -> {
            return new Scope(
                scope.getScopeName(),
                user,
                new Application(scope.getApplicationName()),
                scope.getRoles().stream().map(role -> {
                    return new Role(role.getName(), role.getDescription());
                }).collect(Collectors.toList())
            );
        }).collect(Collectors.toList());
    }

    
}
