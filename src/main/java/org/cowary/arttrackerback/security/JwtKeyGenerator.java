package org.cowary.arttrackerback.security;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;

@Component
public class JwtKeyGenerator {
    @Getter
    private Key signingKey;
    private String base64EncodedSigningKey;

    @PostConstruct
    public void generateKey() {
        this.signingKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
        this.base64EncodedSigningKey = Base64.getEncoder().encodeToString(this.signingKey.getEncoded());
    }

    public String getBase64EncodedKey() {
        return base64EncodedSigningKey;
    }
}
