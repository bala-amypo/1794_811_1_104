package com.example.demo.controller;

import com.example.demo.models.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Profile Endpoints")
public class EmployeeProfileController {
    private final EmployeeProfileService service;

    public EmployeeProfileController(EmployeeProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmployeeProfile> create(@RequestBody EmployeeProfile employee) {
        return ResponseEntity.ok(service.createEmployee(employee));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeProfile>> getAll() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<EmployeeProfile> updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(service.updateEmployeeStatus(id, active));
    }
}