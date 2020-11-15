package com.aeox.auth.dto.signup;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateScopeRoleRequest {
    @NotBlank
    private String name;
    
    private String description;
}
