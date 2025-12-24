@PostMapping("/login")
    @Operation(summary = "Authenticate and get JWT token")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        UserAccount user = userAccountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Invalid credentials");
        }

        String token = jwtProvider.generateToken(user);
        // Returning only the token as shown in your screenshot
        return ResponseEntity.ok(new AuthResponse(token));
    }