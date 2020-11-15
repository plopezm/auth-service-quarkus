
package com.aeox.auth.config.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SecurityUtils {
    public static String hashPasswordUsingSalt(final String password, final byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return Base64.getEncoder().encodeToString(factory.generateSecret(spec).getEncoded());
    }
}
