package com.aeox.auth.dto.signup;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class CreateScopeRequest {
    @NotBlank
    private String applicationName;

    @NotEmpty
    private List<String> roles;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(final List<String> roles) {
        this.roles = roles;
    }

}
