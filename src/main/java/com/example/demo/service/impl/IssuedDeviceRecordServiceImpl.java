package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.IssuedDeviceRecord;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

    private final IssuedDeviceRecordRepository issuedRepo;

    public IssuedDeviceRecordServiceImpl(IssuedDeviceRecordRepository issuedRepo) {
        this.issuedRepo = issuedRepo;
    }

    @Override
    public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {
        return issuedRepo.save(record);
    }

    @Override
    public IssuedDeviceRecord returnDevice(Long recordId) {

        IssuedDeviceRecord record = issuedRepo.findById(recordId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Issued device record not found"));

        if (record.getReturnedDate() != null) {
            throw new BadRequestException("Device already returned");
        }

        record.setReturnedDate(LocalDate.now());
        return issuedRepo.save(record);
    }

    @Override
    public List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId) {
        return issuedRepo.findByEmployeeId(employeeId);
    }
}
