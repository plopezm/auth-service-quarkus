package com.aeox.auth.dto.signup;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class SignupScopeRequest {
    @NotBlank
    private String scopeName;

    @NotBlank
    private String applicationName;

    @NotEmpty
    private List<SignupScopeRoleRequest> roles;

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
    }

    public List<SignupScopeRoleRequest> getRoles() {
        return roles;
    }

    public void setRoles(final List<SignupScopeRoleRequest> roles) {
        this.roles = roles;
    }

}
