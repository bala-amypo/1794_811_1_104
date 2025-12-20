package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.DeviceCatalogItem;
import com.example.demo.models.EmployeeProfile;
import com.example.demo.models.PolicyRule;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private final EmployeeProfileRepository employeeProfileRepository;
    private final DeviceCatalogItemRepository deviceCatalogItemRepository;
    private final IssuedDeviceRecordRepository issuedDeviceRecordRepository;
    private final PolicyRuleRepository policyRuleRepository;

    public EligibilityCheckServiceImpl(
            EmployeeProfileRepository employeeProfileRepository,
            DeviceCatalogItemRepository deviceCatalogItemRepository,
            IssuedDeviceRecordRepository issuedDeviceRecordRepository,
            PolicyRuleRepository policyRuleRepository) {

        this.employeeProfileRepository = employeeProfileRepository;
        this.deviceCatalogItemRepository = deviceCatalogItemRepository;
        this.issuedDeviceRecordRepository = issuedDeviceRecordRepository;
        this.policyRuleRepository = policyRuleRepository;
    }

    @Override
    public boolean checkEligibility(Long employeeId, Long deviceId) {

        EmployeeProfile employee = employeeProfileRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        DeviceCatalogItem device = deviceCatalogItemRepository.findById(deviceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Device not found"));

        List<PolicyRule> rules = policyRuleRepository.findAll();

        for (PolicyRule rule : rules) {
            if (!rule.isEligible(employee, device)) {
                return false;
            }
        }
        return true;
    }
}
