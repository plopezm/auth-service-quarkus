package com.aeox.auth.dto.application;

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
public class CreateApplicationRequest {
    @NotBlank
    private String name;
    private String description;
    @NotEmpty
    private List<String> roles;
}
