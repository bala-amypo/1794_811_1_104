package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private final EmployeeProfileRepository employeeRepo;
    private final DeviceCatalogItemRepository deviceRepo;
    private final IssuedDeviceRecordRepository issuedRepo;
    private final PolicyRuleRepository ruleRepo;
    private final EligibilityCheckRecordRepository checkRepo;

    public EligibilityCheckServiceImpl(
            EmployeeProfileRepository employeeRepo,
            DeviceCatalogItemRepository deviceRepo,
            IssuedDeviceRecordRepository issuedRepo,
            PolicyRuleRepository ruleRepo,
            EligibilityCheckRecordRepository checkRepo) {

        this.employeeRepo = employeeRepo;
        this.deviceRepo = deviceRepo;
        this.issuedRepo = issuedRepo;
        this.ruleRepo = ruleRepo;
        this.checkRepo = checkRepo;
    }

    @Override
    public EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId) {

        EligibilityCheckRecord record = new EligibilityCheckRecord();
        record.setEmployeeId(employeeId);
        record.setDeviceItemId(deviceItemId);

        EmployeeProfile employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        DeviceCatalogItem device = deviceRepo.findById(deviceItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));

        if (!employee.getActive()) {
            record.setIsEligible(false);
            record.setReason("Employee inactive");
            return checkRepo.save(record);
        }

        if (!device.getActive()) {
            record.setIsEligible(false);
            record.setReason("Device inactive");
            return checkRepo.save(record);
        }

        int activeDevices = issuedRepo.countActiveDevicesForEmployee(employeeId);
        if (activeDevices >= device.getMaxAllowedPerEmployee()) {
            record.setIsEligible(false);
            record.setReason("maxAllowedPerEmployee exceeded");
            return checkRepo.save(record);
        }

        boolean alreadyIssued = issuedRepo
                .findActiveByEmployeeAndDevice(employeeId, deviceItemId)
                .isPresent();

        if (alreadyIssued) {
            record.setIsEligible(false);
            record.setReason("Conflicting active issuance");
            return checkRepo.save(record);
        }

        List<PolicyRule> activeRules = ruleRepo.findByActiveTrue();
        for (PolicyRule rule : activeRules) {

            if (rule.getAppliesToRole() != null &&
                    !rule.getAppliesToRole().equals(employee.getJobRole())) {
                continue;
            }

            if (rule.getAppliesToDepartment() != null &&
                    !rule.getAppliesToDepartment().equals(employee.getDepartment())) {
                continue;
            }

            if (activeDevices >= rule.getMaxDevicesAllowed()) {
                record.setIsEligible(false);
                record.setReason("Policy rule limit exceeded");
                return checkRepo.save(record);
            }
        }

        record.setIsEligible(true);
        record.setReason("Eligible");
        return checkRepo.save(record);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return checkRepo.findByEmployeeId(employeeId);
    }
}
