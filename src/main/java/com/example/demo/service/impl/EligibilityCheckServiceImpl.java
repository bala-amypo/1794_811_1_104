package com.example.demo.service.impl;

import com.example.demo.models.*;
import com.example.demo.repository.*;
import com.example.demo.service.EligibilityCheckService;
import com.example.demo.exception.ResourceNotFoundException;
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
        EmployeeProfile emp = empRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee"));
        DeviceCatalogItem dev = devRepo.findById(deviceItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Device"));
        
        boolean eligible = true;
        String reason = "Eligible";

        if (!emp.getActive()) { 
            eligible = false; 
            reason = "Employee not active"; 
        } else if (dev.getActive() != null && !dev.getActive()) { 
            eligible = false; 
            reason = "Device inactive"; 
        } else if (!issueRepo.findActiveByEmployeeAndDevice(employeeId, deviceItemId).isEmpty()) {
            eligible = false;
            reason = "active issuance exists";
        } else {
            // Check limits
            long activeCount = issueRepo.countActiveDevicesForEmployee(employeeId);
            if (activeCount >= dev.getMaxAllowedPerEmployee()) {
                eligible = false;
                reason = "Maximum allowed devices reached for this item";
            } else {
                // Check Policy Rules
                List<PolicyRule> activeRules = policyRepo.findByActiveTrue();
                for (PolicyRule rule : activeRules) {
                    boolean roleMatch = rule.getAppliesToRole() == null || rule.getAppliesToRole().equals(emp.getJobRole());
                    boolean deptMatch = rule.getAppliesToDepartment() == null || rule.getAppliesToDepartment().equals(emp.getDepartment());
                    
                    if (roleMatch && deptMatch && activeCount >= rule.getMaxDevicesAllowed()) {
                        eligible = false;
                        reason = "Policy violation: Max limit reached";
                        break;
                    }
                }
            }
        }

        EligibilityCheckRecord record = new EligibilityCheckRecord();
        record.setEmployeeId(employeeId);
        record.setDeviceItemId(deviceItemId);
        record.setIsEligible(eligible);
        record.setReason(reason);
        return checkRepo.save(record);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return checkRepo.findByEmployeeId(employeeId);
    }
}