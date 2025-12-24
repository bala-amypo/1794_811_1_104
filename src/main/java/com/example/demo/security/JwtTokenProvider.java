package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String secret;
    private final long validityInMs;

    public JwtTokenProvider() {
        this.secret = "mySecretKeyMySecretKeyMySecretKeyMySecretKey";
        this.validityInMs = 3600000;
    }

    // Required Constructor for Tests
    public JwtTokenProvider(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    public String generateToken(String email, String role) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validityInMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}