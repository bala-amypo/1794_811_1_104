package com.example.demo.models;
import jakarta.persistence.*;

@Entity
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true) private String email;
    private String passwordHash;
    private String role; // ADMIN, IT_OPERATOR, AUDITOR
    private Boolean active = true;
    // Getters and Setters ...
}