package com.example.demo.config;

import com.example.demo.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        // ⚠️ Must match constructor used in tests
        return new JwtTokenProvider(
                "ChangeThisSecretKeyForJwt123456789012345",
                3600000
        );
    }
}
