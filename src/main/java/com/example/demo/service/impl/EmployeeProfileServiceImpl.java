package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.models.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.exception.*;

@Service
public class EmployeeProfileServiceImpl implements com.example.demo.service.EmployeeProfileService {

    private final EmployeeProfileRepository repo;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repo) {
        this.repo = repo;
    }

    public EmployeeProfile createEmployee(EmployeeProfile e) {
        repo.findByEmployeeId(e.getEmployeeId())
            .ifPresent(x -> { throw new BadRequestException("EmployeeId already exists"); });
        return repo.save(e);
    }

    public EmployeeProfile getEmployeeById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    public List<EmployeeProfile> getAllEmployees() {
        return repo.findAll();
    }

    public EmployeeProfile updateEmployeeStatus(Long id, boolean active) {
        EmployeeProfile e = getEmployeeById(id);
        e.setActive(active);
        return repo.save(e);
    }
}
