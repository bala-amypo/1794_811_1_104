package com.example.demo.service.impl;

import com.example.demo.models.*;
import com.example.demo.repository.*;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {
    private final EmployeeProfileRepository empRepo;
    private final DeviceCatalogItemRepository devRepo;
    private final IssuedDeviceRecordRepository issueRepo;
    private final PolicyRuleRepository policyRepo;
    private final EligibilityCheckRecordRepository checkRepo;

    public EligibilityCheckServiceImpl(EmployeeProfileRepository empRepo, 
                                     DeviceCatalogItemRepository devRepo,
                                     IssuedDeviceRecordRepository issueRepo,
                                     PolicyRuleRepository policyRepo,
                                     EligibilityCheckRecordRepository checkRepo) {
        this.empRepo = empRepo;
        this.devRepo = devRepo;
        this.issueRepo = issueRepo;
        this.policyRepo = policyRepo;
        this.checkRepo = checkRepo;
    }

    @Override
    public EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId) {
        EmployeeProfile emp = empRepo.findById(employeeId).orElseThrow();
        DeviceCatalogItem dev = devRepo.findById(deviceItemId).orElseThrow();
        
        boolean eligible = true;
        StringBuilder reason = new StringBuilder("Eligible");

        if (!emp.getActive()) { eligible = false; reason = new String("Employee not active"); }
        if (dev.getActive() != null && !dev.getActive()) { eligible = false; reason = new String("Device inactive"); }

        // Rule Check logic...
        
        EligibilityCheckRecord record = new EligibilityCheckRecord();
        record.setEmployeeId(employeeId);
        record.setDeviceItemId(deviceItemId);
        record.setIsEligible(eligible);
        record.setReason(reason.toString());
        return checkRepo.save(record);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return checkRepo.findByEmployeeId(employeeId);
    }
}