package com.example.demo.controller;

import com.example.demo.models.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserAccountRepository userAccountRepository,
                          PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(@RequestBody UserAccount user) {

        // encrypt password
        user.setPasswordHash(
                passwordEncoder.encode(user.getPasswordHash())
        );

        // defaults
        user.setActive(true);
        if (user.getRole() == null) {
            user.setRole("USER");
        }

        UserAccount savedUser = userAccountRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        UserAccount user = userAccountRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        // NO JWT – simple success response
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("email", user.getEmail());
        response.put("role", user.getRole());

        return ResponseEntity.ok(response);
    }
}
