package com.example.demo.repository;

import com.example.demo.models.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssuedDeviceRecordRepository extends JpaRepository<IssuedDeviceRecord, Long> {

    long countActiveDevicesForEmployee(Long employeeId);

    List<IssuedDeviceRecord> findActiveByEmployeeAndDevice(
            Long employeeId,
            Long deviceItemId
    );
}
