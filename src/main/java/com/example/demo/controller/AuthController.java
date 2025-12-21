package com.example.demo.controller;

import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.UserAccount;
import com.example.demo.repository.UserAccountRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountRepository userRepo;

    // ✅ Local encoder (NO Spring bean required)
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserAccountRepository userRepo) {
        this.userRepo = userRepo;
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {

        user.setPasswordHash(
                passwordEncoder.encode(user.getPasswordHash())
        );

        user.setActive(true);
        if (user.getRole() == null) {
            user.setRole("USER");
        }

        return userRepo.save(user);
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> req) {

        return Map.of("message", "Login successful");
    }
}
