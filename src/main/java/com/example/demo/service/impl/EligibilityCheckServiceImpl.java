package com.example.demo.service.impl;

import com.example.demo.repository.*;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.stereotype.Service;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private final IssuedDeviceRecordRepository issuedRepo;

    // ✅ NEW constructor (used by Spring)
    public EligibilityCheckServiceImpl(
            IssuedDeviceRecordRepository issuedRepo
    ) {
        this.issuedRepo = issuedRepo;
    }

    // 🔁 OLD constructor REQUIRED BY TESTS
    public EligibilityCheckServiceImpl(
            EmployeeProfileRepository employeeRepo,
            DeviceCatalogItemRepository deviceRepo,
            IssuedDeviceRecordRepository issuedRepo,
            PolicyRuleRepository policyRepo,
            EligibilityCheckRecordRepository eligibilityRepo
    ) {
        this.issuedRepo = issuedRepo;
    }

    @Override
    public boolean isEligible(Long employeeId, Long deviceId) {
        return issuedRepo
                .findActiveByEmployeeAndDevice(employeeId, deviceId)
                .isEmpty();
    }
}
