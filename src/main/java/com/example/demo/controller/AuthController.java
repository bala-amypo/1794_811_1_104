package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.models.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.exception.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Endpoints", description = "Endpoints for user registration and login")
public class AuthController {

    private final UserAccountRepository userAccountRepository;
    private final JwtTokenProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection (Strict Requirement for Testing)
    public AuthController(UserAccountRepository userAccountRepository, 
                          JwtTokenProvider jwtProvider, 
                          PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new UserAccount")
    public ResponseEntity<UserAccount> register(@RequestBody UserAccount user) {
        // Step 1.6: email must be unique
        if (userAccountRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }
        
        // Step 1.6: Password must be hashed using BCrypt before storage
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        
        return ResponseEntity.ok(userAccountRepository.save(user));
    }

    @PostMapping("/login")
    @Operation(summary = "Login and receive JWT Token")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        UserAccount user = userAccountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        // Password verification
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Invalid email or password");
        }

        // Generate JWT Token
        String token = jwtProvider.generateToken(user);
        
        // Returning AuthResponse DTO (token, userId, email, role)
        return ResponseEntity.ok(new AuthResponse(
                token, 
                user.getId(), 
                user.getEmail(), 
                user.getRole()
        ));
    }

    @GetMapping("/me")
    @Operation(summary = "Get current authenticated user info (Requires JWT)")
    public ResponseEntity<UserAccount> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        // Extract token from Bearer prefix
        String token = authHeader.substring(7);
        
        // Extract email from token claims
        String email = jwtProvider.getUsername(token);
        
        return userAccountRepository.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}