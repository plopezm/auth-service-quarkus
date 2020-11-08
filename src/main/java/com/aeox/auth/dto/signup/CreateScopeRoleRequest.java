package com.aeox.auth.dto.signup;

import javax.validation.constraints.NotBlank;

public class CreateScopeRoleRequest {
    @NotBlank
    private String name;
    
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
