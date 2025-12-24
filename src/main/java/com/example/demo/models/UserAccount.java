package com.example.demo.models;

import jakarta.persistence.*;

/**
 * STEP 1.6 - UserAccount Entity
 * Rules: 
 * - Email must be unique.
 * - Password stored as hash.
 * - Roles: ADMIN / IT_OPERATOR / AUDITOR
 */
@Entity
@Table(name = "user_accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String role; // ADMIN, IT_OPERATOR, or AUDITOR

    @Column(nullable = false)
    private Boolean active = true;

    // Default Constructor (Required by JPA)
    public UserAccount() {
    }

    // Constructor for quick instantiation
    public UserAccount(String fullName, String email, String passwordHash, String role, Boolean active) {
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.active = active;
    }

    // GETTERS AND SETTERS (Mandatory for JPA and JSON Mapping)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}