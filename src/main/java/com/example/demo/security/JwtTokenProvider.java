package com.example.demo.security;

import com.example.demo.models.UserAccount;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String secret;
    private final long validityInMs;

    public JwtTokenProvider(@Value("${jwt.secret:mySecretKeyForJwtAuthenticationWhichIsLongEnough}") String secret,
                            @Value("${jwt.validity:3600000}") long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    public String generateToken(UserAccount user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("role", user.getRole());
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validityInMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) { return false; }
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}