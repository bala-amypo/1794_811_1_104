package com.example.demo.service.impl;

import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.stereotype.Service;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private final IssuedDeviceRecordRepository issuedDeviceRecordRepository;

    public EligibilityCheckServiceImpl(
            IssuedDeviceRecordRepository issuedDeviceRecordRepository
    ) {
        this.issuedDeviceRecordRepository = issuedDeviceRecordRepository;
    }

    @Override
    public boolean isEligible(Long employeeId, Long deviceId) {

        return issuedDeviceRecordRepository
                .findByEmployeeIdAndDeviceIdAndStatus(
                        employeeId,
                        deviceId,
                        "ISSUED"
                )
                .isEmpty();
    }
}
