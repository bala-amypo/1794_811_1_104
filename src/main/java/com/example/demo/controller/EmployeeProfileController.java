package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService service;

    public EmployeeProfileController(EmployeeProfileService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeProfile create(@RequestBody EmployeeProfile e) {
        return service.createEmployee(e);
    }

    @GetMapping("/{id}")
    public EmployeeProfile get(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeProfile> all() {
        return service.getAllEmployees();
    }

    @PutMapping("/{id}/status")
    public EmployeeProfile status(@PathVariable Long id, @RequestParam boolean active) {
        return service.updateEmployeeStatus(id, active);
    }
}
