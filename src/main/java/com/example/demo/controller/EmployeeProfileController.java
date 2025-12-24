package com.example.demo.controller;

import com.example.demo.models.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeService employeeService;

    public EmployeeProfileController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }
}
