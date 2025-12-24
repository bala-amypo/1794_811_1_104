package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.models.IssuedDeviceRecord;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {
    private final IssuedDeviceRecordRepository repository;

    public IssuedDeviceRecordServiceImpl(IssuedDeviceRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {
        record.setStatus("ISSUED");
        record.setIssuedDate(LocalDate.now());
        return repository.save(record);
    }

    @Override
    public IssuedDeviceRecord returnDevice(Long recordId) {
        IssuedDeviceRecord record = repository.findById(recordId).orElseThrow();
        if ("RETURNED".equals(record.getStatus())) {
            throw new BadRequestException("already returned");
        }
        record.setStatus("RETURNED");
        record.setReturnedDate(LocalDate.now());
        return repository.save(record);
    }

    @Override
    public List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId) {
        return null; // Implement repository method if needed
    }
}