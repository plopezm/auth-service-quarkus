package com.aeox.auth.dto.signup;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateScopeRequest {
    @NotBlank
    private String applicationName;
    @NotEmpty
    private List<String> roles;
}
