package com.aeox.auth.config;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "org.aeox.auth")
public class AuthServiceProperties {
    private String issuer;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(final String issuer) {
        this.issuer = issuer;
    }
}
