package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.IssuedDeviceRecord;

public interface IssuedDeviceRecordRepository
        extends JpaRepository<IssuedDeviceRecord, Long> {

    int countActiveDevicesForEmployee(Long employeeId);

    Optional<IssuedDeviceRecord> findActiveByEmployeeAndDevice(
            Long employeeId, Long deviceItemId
    );

    List<IssuedDeviceRecord> findByEmployeeId(Long employeeId);
}
