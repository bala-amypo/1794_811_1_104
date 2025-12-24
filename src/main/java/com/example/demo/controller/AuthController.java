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

/**
 * STEP 2 & 5 – AuthController
 * Handles user registration and JWT-based authentication.
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Endpoints")
public class AuthController {

    private final UserAccountRepository userAccountRepository;
    private final JwtTokenProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    // CONSTRUCTOR INJECTION - Required for automated testing
    public AuthController(UserAccountRepository userAccountRepository, 
                          JwtTokenProvider jwtProvider, 
                          PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * POST /auth/register
     * Logic: Verifies unique email, hashes password, and saves the account.
     */
    @PostMapping("/register")
    @Operation(summary = "Register a new UserAccount")
    public ResponseEntity<UserAccount> register(@RequestBody UserAccount user) {
        if (userAccountRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }
        
        // Technical Constraint: Password must be hashed via BCrypt before storage
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        
        return ResponseEntity.ok(userAccountRepository.save(user));
    }

    /**
     * POST /auth/login
     * Logic: Validates credentials and returns a JWT token in AuthResponse.
     */
    @PostMapping("/login")
    @Operation(summary = "Login and receive JWT Token")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        UserAccount user = userAccountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Invalid credentials");
        }

        String token = jwtProvider.generateToken(user);
        
        // AuthResponse matches Section 3 DTO requirements: token, userId, email, role
        return ResponseEntity.ok(new AuthResponse(
                token, 
                user.getId(), 
                user.getEmail(), 
                user.getRole()
        ));
    }

    /**
     * GET /auth/me
     * Logic: Extracts user details from the JWT token for the current session.
     */
    @GetMapping("/me")
    @Operation(summary = "Get current authenticated user info (Requires JWT)")
    public ResponseEntity<UserAccount> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BadRequestException("Missing or invalid Authorization header");
        }
        
        String token = authHeader.substring(7);
        String email = jwtProvider.getUsername(token);
        
        return userAccountRepository.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }
}