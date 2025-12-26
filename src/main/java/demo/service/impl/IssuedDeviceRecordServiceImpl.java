package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.repository.*;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.stereotype.Service;

@Service
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

    private final IssuedDeviceRecordRepository issuedRepo;
    private final EmployeeProfileRepository employeeRepo;
    private final DeviceCatalogItemRepository deviceRepo;

    public IssuedDeviceRecordServiceImpl(
            IssuedDeviceRecordRepository issuedRepo,
            EmployeeProfileRepository employeeRepo,
            DeviceCatalogItemRepository deviceRepo) {
        this.issuedRepo = issuedRepo;
        this.employeeRepo = employeeRepo;
        this.deviceRepo = deviceRepo;
    }

    @Override
    public IssuedDeviceRecord issuedDevice(IssuedDeviceRecord record) {
        return issuedRepo.save(record);
    }

    @Override
    public IssuedDeviceRecord returnDevice(Long recordId) {
        IssuedDeviceRecord record = issuedRepo.findById(recordId)
                .orElseThrow(() -> new BadRequestException("Record not found"));

        if ("RETURNED".equals(record.getStatus())) {
            throw new BadRequestException("Device already returned");
        }

        record.setStatus("RETURNED");
        return issuedRepo.save(record);
    }
}
