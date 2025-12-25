package com.example.demo.service.impl;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

    private final IssuedDeviceRecordRepository repo;

    public IssuedDeviceRecordServiceImpl(IssuedDeviceRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public IssuedDeviceRecord issuedDevice(IssuedDeviceRecord record) {
        record.setStatus("ISSUED");
        return repo.save(record);
    }

    @Override
    public IssuedDeviceRecord returnDevice(Long recordId) {
        IssuedDeviceRecord record = repo.findById(recordId).orElseThrow();
        record.setStatus("RETURNED");
        return repo.save(record);
    }

    // ✅ REQUIRED METHOD (FIXES ERROR)
    @Override
    public List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId) {
        return repo.findAll()
                .stream()
                .filter(r -> r.getEmployeeId().equals(employeeId))
                .toList();
    }
}
