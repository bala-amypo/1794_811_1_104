package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_profiles")
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeId;   // ✅ REQUIRED
    private String name;
    private String email;        // ✅ REQUIRED
    private String role;
    private String department;
    private Boolean active = true;

    private LocalDateTime createdAt; // ✅ REQUIRED

    /* ===== GETTERS ===== */

    public Long getId() {
        return id;
    }

    public String getEmployeeId() {          // ✅ REQUIRED
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {               // ✅ REQUIRED
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /* ===== SETTERS ===== */

    public void setId(Long id) {
        this.id = id;
    }

    public vo
