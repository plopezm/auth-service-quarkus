package com.aeox.auth.dto.application;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class CreateApplicationRequest {
    @NotBlank
    public String name;
    public String description;
    @NotEmpty
    public List<String> roles;
}
