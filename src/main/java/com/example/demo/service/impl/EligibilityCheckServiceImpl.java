package com.example.demo.service.impl;

import com.example.demo.models.*;
import com.example.demo.repository.*;
import com.example.demo.service.EligibilityCheckService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private final EmployeeProfileRepository employeeRepo;
    private final DeviceCatalogItemRepository deviceRepo;
    private final IssuedDeviceRecordRepository issuedRepo;
    private final PolicyRuleRepository policyRepo;
    private final EligibilityCheckRecordRepository eligibilityRepo;

    // ✅ Constructor Injection
    public EligibilityCheckServiceImpl(
            EmployeeProfileRepository employeeRepo,
            DeviceCatalogItemRepository deviceRepo,
            IssuedDeviceRecordRepository issuedRepo,
            PolicyRuleRepository policyRepo,
            EligibilityCheckRecordRepository eligibilityRepo) {

        this.employeeRepo = employeeRepo;
        this.deviceRepo = deviceRepo;
        this.issuedRepo = issuedRepo;
        this.policyRepo = policyRepo;
        this.eligibilityRepo = eligibilityRepo;
    }

    @Override
    public EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId) {

        EligibilityCheckRecord record = new EligibilityCheckRecord();
        record.setEmployeeId(employeeId);
        record.setDeviceItemId(deviceItemId);

        Optional<EmployeeProfile> empOpt = employeeRepo.findById(employeeId);
        Optional<DeviceCatalogItem> devOpt = deviceRepo.findById(deviceItemId);

        // ❌ Employee or device not found
        if (empOpt.isEmpty() || devOpt.isEmpty()) {
            record.setIsEligible(false);
            record.setReason("not found");
            return eligibilityRepo.save(record);
        }

        EmployeeProfile emp = empOpt.get();
        DeviceCatalogItem dev = devOpt.get();

        // ❌ Employee inactive
        if (!emp.getActive()) {
            record.setIsEligible(false);
            record.setReason("not active");
            return eligibilityRepo.save(record);
        }

        // ❌ Device inactive
        if (!dev.getActive()) {
            record.setIsEligible(false);
            record.setReason("inactive");
            return eligibilityRepo.save(record);
        }

        // ❌ Active issuance already exists
        if (!issuedRepo.findActiveByEmployeeAndDevice(employeeId, deviceItemId).isEmpty()) {
            record.setIsEligible(false);
            record.setReason("active issuance");
            return eligibilityRepo.save(record);
        }

        long activeCount = issuedRepo.countActiveDevicesForEmployee(employeeId);

        // ❌ Max devices per employee exceeded
        if (dev.getMaxAllowedPerEmployee() != null &&
            activeCount >= dev.getMaxAllowedPerEmployee()) {

            record.setIsEligible(false);
            record.setReason("Maximum allowed devices");
            return eligibilityRepo.save(record);
        }

        // ❌ Policy rule violations
        List<PolicyRule> activeRules = policyRepo.findByActiveTrue();

        for (PolicyRule rule : activeRules) {

            boolean deptMatch =
                    rule.getAppliesToDepartment() == null ||
                    rule.getAppliesToDepartment().equals(emp.getDepartment());

            boolean roleMatch =
                    rule.getAppliesToRole() == null ||
                    rule.getAppliesToRole().equals(emp.getJobRole());

            if (deptMatch && roleMatch &&
                rule.getMaxDevicesAllowed() != null &&
                activeCount >= rule.getMaxDevicesAllowed()) {

                record.setIsEligible(false);
                record.setReason("Policy violation");
                return eligibilityRepo.save(record);
            }
        }

        // ✅ Eligible
        record.setIsEligible(true);
        record.setReason("Eligible");
        return eligibilityRepo.save(record);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return eligibilityRepo.findByEmployeeId(employeeId);
    }
}
