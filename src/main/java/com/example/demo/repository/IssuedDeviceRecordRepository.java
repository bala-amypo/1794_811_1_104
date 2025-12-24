package com.example.demo.repository;

import com.example.demo.models.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IssuedDeviceRecordRepository extends JpaRepository<IssuedDeviceRecord, Long> {
    @Query("SELECT COUNT(r) FROM IssuedDeviceRecord r WHERE r.employeeId = ?1 AND r.status = 'ISSUED'")
    Long countActiveDevicesForEmployee(Long employeeId);

    @Query("SELECT r FROM IssuedDeviceRecord r WHERE r.employeeId = ?1 AND r.deviceItemId = ?2 AND r.status = 'ISSUED'")
    List<IssuedDeviceRecord> findActiveByEmployeeAndDevice(Long employeeId, Long deviceItemId);
}