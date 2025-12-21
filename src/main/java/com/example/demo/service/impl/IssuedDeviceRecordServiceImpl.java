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

        // 🔒 Backend controls these fields
        record.setId(null);                       // DB generates ID
        record.setIssuedDate(LocalDate.now());    // issue date = today
        record.setReturnedDate(null);             // ✅ MUST BE NULL
        record.setStatus("ISSUED");               // issued state

        return repo.save(record);
    }

    // ================= RETURN DEVICE =================
    @Override
    public IssuedDeviceRecord returnDevice(Long recordId) {

        IssuedDeviceRecord record = repo.findById(recordId)
                .orElseThrow(() ->
                        new BadRequestException("Issued device record not found"));

        if (record.getReturnedDate() != null) {
            throw new BadRequestException("Device already returned");
        }

        record.setReturnedDate(LocalDate.now());  // ✅ SET ONLY HERE
        record.setStatus("RETURNED");

        return repo.save(record);
    }

    // ================= GET BY EMPLOYEE =================
    @Override
    public List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }
}
