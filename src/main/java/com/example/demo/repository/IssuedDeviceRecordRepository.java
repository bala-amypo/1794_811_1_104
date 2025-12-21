package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.IssuedDeviceRecord;

@Repository
public interface IssuedDeviceRecordRepository
        extends JpaRepository<IssuedDeviceRecord, Long> {

    // REQUIRED BY TEST CASE
    @Query("""
        SELECT COUNT(r)
        FROM IssuedDeviceRecord r
        WHERE r.employeeId = :employeeId
          AND r.returnedDate IS NULL
    """)
    Long countActiveDevicesForEmployee(Long employeeId);

    // REQUIRED BY TEST CASE
    @Query("""
        SELECT r
        FROM IssuedDeviceRecord r
        WHERE r.employeeId = :employeeId
          AND r.deviceItemId = :deviceItemId
          AND r.returnedDate IS NULL
    """)
    Optional<IssuedDeviceRecord> findActiveByEmployeeAndDevice(
            Long employeeId,
            Long deviceItemId
    );
}
