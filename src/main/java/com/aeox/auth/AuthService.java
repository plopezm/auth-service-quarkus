package com.aeox.auth;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

@OpenAPIDefinition(
    info = @Info(
        title="Auth service",
        version = "1.0.0",
        contact = @Contact(
            name = "Pablo López Martínez",
            url = "https://github.com/plopezm",
            email = "pabloplm@gmail.com"),
        license = @License(
            name = "MIT",
            url = "https://opensource.org/licenses/MIT")),
    components = @Components(
        securitySchemes = {
            @SecurityScheme(
                securitySchemeName = "bearerAuth",
                scheme = "bearer",
                bearerFormat = "JWT",
                type = SecuritySchemeType.HTTP
            )
        }
    ),    
    security = {
        @SecurityRequirement(
            name = "bearerAuth"
        )
    }
)
public class AuthService extends Application {   
}
