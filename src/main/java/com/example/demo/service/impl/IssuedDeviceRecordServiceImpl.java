package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.EmployeeProfile;
import com.example.demo.models.IssuedDeviceRecord;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.IssuedDeviceRecordRepository;

@Service
public class IssuedDeviceRecordServiceImpl {

    private final IssuedDeviceRecordRepository repo;
    private final EmployeeProfileRepository employeeRepo;

    public IssuedDeviceRecordServiceImpl(
            IssuedDeviceRecordRepository repo,
            EmployeeProfileRepository employeeRepo) {
        this.repo = repo;
        this.employeeRepo = employeeRepo;
    }

    public List<IssuedDeviceRecord> getIssuedDevicesByEmployeeId(Long employeeId) {

        EmployeeProfile employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return repo.findByEmployee(employee);
    }
}
