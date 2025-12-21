package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.models.IssuedDeviceRecord;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.IssuedDeviceRecordService;

@Service
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

    private final IssuedDeviceRecordRepository repo;

    public IssuedDeviceRecordServiceImpl(IssuedDeviceRecordRepository repo) {
        this.repo = repo;
    }

    // ================= ISSUE DEVICE =================
    @Override
    public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {

        // ❌ Ignore client-sent values completely
        record.setId(null);                 // DB generates ID
        record.setIssuedDate(LocalDate.now());
        record.setReturnedDate(null);
        record.setStatus("ISSUED");

        return repo.save(record);
    }

    // ================= RETURN DEVICE =================
    @Override
    public IssuedDeviceRecord returnDevice(Long recordId) {

        IssuedDeviceRecord record = repo.findById(recordId)
                .orElseThrow(() ->
                        new BadRequestException("already returned"));

        if (record.getReturnedDate() != null) {
            throw new BadRequestException("already returned");
        }

        record.setReturnedDate(LocalDate.now());
        record.setStatus("RETURNED");

        return repo.save(record);
    }

    // ================= GET BY EMPLOYEE =================
    @Override
    public List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }
}
