package com.aeox.auth.dto.signup;

import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.aeox.auth.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    private List<CreateScopeRequest> scopes;

    @JsonbTransient
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
}
