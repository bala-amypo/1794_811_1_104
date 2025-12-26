package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private final EmployeeProfileRepository employeeRepo;
    private final DeviceCatalogItemRepository deviceRepo;
    private final IssuedDeviceRecordRepository issuedRepo;
    private final PolicyRuleRepository policyRepo;
    private final EligibilityCheckRecordRepository eligibilityRepo;

    public EligibilityCheckServiceImpl(
            EmployeeProfileRepository employeeRepo,
            DeviceCatalogItemRepository deviceRepo,
            IssuedDeviceRecordRepository issuedRepo,
            PolicyRuleRepository policyRepo,
            EligibilityCheckRecordRepository eligibilityRepo
    ) {
        this.employeeRepo = employeeRepo;
        this.deviceRepo = deviceRepo;
        this.issuedRepo = issuedRepo;
        this.policyRepo = policyRepo;
        this.eligibilityRepo = eligibilityRepo;
    }

    @Override
    public EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId) {

        EligibilityCheckRecord rec = new EligibilityCheckRecord();
        rec.setEmployeeId(employeeId);
        rec.setDeviceItemId(deviceItemId);

        Optional<EmployeeProfile> empOpt = employeeRepo.findById(employeeId);
        Optional<DeviceCatalogItem> devOpt = deviceRepo.findById(deviceItemId);

        if (empOpt.isEmpty() || devOpt.isEmpty()) {
            rec.setIsEligible(false);
            rec.setReason("Employee or Device not found");
            return eligibilityRepo.save(rec);
        }

        EmployeeProfile emp = empOpt.get();
        DeviceCatalogItem dev = devOpt.get();

        if (!emp.getActive()) {
            rec.setIsEligible(false);
            rec.setReason("Employee not active");
            return eligibilityRepo.save(rec);
        }

        if (!dev.getActive()) {
            rec.setIsEligible(false);
            rec.setReason("Device inactive");
            return eligibilityRepo.save(rec);
        }

        if (!issuedRepo.findActiveByEmployeeAndDevice(employeeId, deviceItemId).isEmpty()) {
            rec.setIsEligible(false);
            rec.setReason("Active issuance already exists");
            return eligibilityRepo.save(rec);
        }

        Long issuedCount = issuedRepo.countActiveDevicesForEmployee(employeeId);

        if (dev.getMaxAllowedPerEmployee() != null &&
                issuedCount >= dev.getMaxAllowedPerEmployee()) {
            rec.setIsEligible(false);
            rec.setReason("Maximum allowed devices reached");
            return eligibilityRepo.save(rec);
        }

        List<PolicyRule> rules = policyRepo.findByActiveTrue();

        for (PolicyRule rule : rules) {

            boolean deptMatch =
                    rule.getAppliesToDepartment() == null ||
                    rule.getAppliesToDepartment().equals(emp.getDepartment());

            boolean roleMatch =
                    rule.getAppliesToRole() == null ||
                    rule.getAppliesToRole().equals(emp.getJobRole());

            if (deptMatch && roleMatch) {
                if (rule.getMaxDevicesAllowed() != null &&
                        issuedCount >= rule.getMaxDevicesAllowed()) {
                    rec.setIsEligible(false);
                    rec.setReason("Policy violation");
                    return eligibilityRepo.save(rec);
                }
            }
        }

        rec.setIsEligible(true);
        rec.setReason("Eligible");
        return eligibilityRepo.save(rec);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return eligibilityRepo.findByEmployeeId(employeeId);
    }
}
