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

    /**
     * CRITICAL REQUIREMENT: 
     * Constructor must accept (String secret, long validityInMs).
     * The @Value annotations allow Spring to inject values from application.properties,
     * while the signature remains compatible with manual instantiation in test suites.
     */
    public JwtTokenProvider(@Value("${jwt.secret:mySecretKeyForJwtAuthenticationWhichIsLongEnough}") String secret,
                            @Value("${jwt.validity:3600000}") long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    public String generateToken(UserAccount user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("role", user.getRole());
        claims.put("userId", user.getId());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            // Log exception if needed: Token expired or signature invalid
            return false;
        }
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRole(String token) {
        return (String) Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }
}