package com.example.demo.controller;

import java.util.Map;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.UserAccount;
import com.example.demo.repository.UserAccountRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountRepository repo;
    private final PasswordEncoder encoder;

    public AuthController(UserAccountRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount u) {
        u.setPasswordHash(encoder.encode(u.getPasswordHash()));
        return repo.save(u);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> req) {
        return Map.of("message", "Login successful");
    }
}
