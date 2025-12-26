package com.example.demo.service.impl;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.stereotype.Service;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private final EmployeeProfileRepository employeeRepo;
    private final IssuedDeviceRecordRepository issuedRepo;
    private final DeviceCatalogItemRepository deviceRepo;

    public EligibilityCheckServiceImpl(
            EmployeeProfileRepository employeeRepo,
            IssuedDeviceRecordRepository issuedRepo,
            DeviceCatalogItemRepository deviceRepo
    ) {
        this.employeeRepo = employeeRepo;
        this.issuedRepo = issuedRepo;
        this.deviceRepo = deviceRepo;
    }

    @Override
    public EligibilityCheckRecord checkEligibility(Long employeeId, Long deviceId) {

        EmployeeProfile employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        long issuedCount = issuedRepo.findAll()
                .stream()
                .filter(r -> r.getEmployeeId().equals(employee.getEmployeeId()))
                .count();

        EligibilityCheckRecord record = new EligibilityCheckRecord();

        // ✅ FIXED TYPES
        record.setEmployeeId(employeeId);
        record.setDeviceId(deviceId);
        record.setEligible(issuedCount < 1);

        return record;
    }
}
