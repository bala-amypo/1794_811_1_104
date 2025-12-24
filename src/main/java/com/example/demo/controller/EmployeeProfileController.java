package com.example.demo.controller;

import com.example.demo.models.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileRepository employeeRepo;

    public EmployeeProfileController(EmployeeProfileRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/{id}")
    public EmployeeProfile getEmployee(
            @PathVariable("id") Long id) {

        return employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
