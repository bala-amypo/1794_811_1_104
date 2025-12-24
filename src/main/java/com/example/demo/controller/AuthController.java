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
@Tag(name = "Authentication Endpoints")
public class AuthController {

    private final UserAccountRepository userAccountRepository;
    private final JwtTokenProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection is a strict requirement (Step 0)
    public AuthController(UserAccountRepository userAccountRepository, 
                          JwtTokenProvider jwtProvider, 
                          PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Requirement: POST /auth/register
     * Logic: Check unique email, hash password, save user.
     */
    @PostMapping("/register")
    @Operation(summary = "Register a new UserAccount")
    public ResponseEntity<UserAccount> register(@RequestBody UserAccount user) {
        if (userAccountRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }
        
        // Technical Constraint: Password must be hashed before saving
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        
        return ResponseEntity.ok(userAccountRepository.save(user));
    }

    /**
     * Requirement: POST /auth/login
     * Logic: Authenticate email/password, return JWT token and user info in AuthResponse.
     */
    @PostMapping("/login")
    @Operation(summary = "Login and receive JWT Token")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        UserAccount user = userAccountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Invalid email or password");
        }

        // Generate token using the logic from Section 8
        String token = jwtProvider.generateToken(user);
        
        // AuthResponse must contain: token, userId, email, and role (Section 3)
        return ResponseEntity.ok(new AuthResponse(
                token, 
                user.getId(), 
                user.getEmail(), 
                user.getRole()
        ));
    }

    /**
     * OPTIONAL: GET /auth/me
     * This is not explicitly in your Step 5 requirements but is useful 
     * for verifying that the 'Authorize' button in Swagger works.
     */
    @GetMapping("/me")
    @Operation(summary = "Get current authenticated user info (Requires JWT)")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) {
        String pureToken = token.substring(7);
        String email = jwtProvider.getUsername(pureToken);
        
        return userAccountRepository.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}