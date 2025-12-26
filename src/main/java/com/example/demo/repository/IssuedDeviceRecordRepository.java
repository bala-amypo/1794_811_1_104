package com.example.demo.repository;

import com.example.demo.model.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IssuedDeviceRecordRepository
        extends JpaRepository<IssuedDeviceRecord, Long> {

    // 🔁 OLD METHOD REQUIRED BY TESTS
    @Query("""
        SELECT r FROM IssuedDeviceRecord r
        WHERE r.employeeId = :employeeId
          AND r.deviceId = :deviceId
          AND r.status = 'ISSUED'
    """)
    List<IssuedDeviceRecord> findActiveByEmployeeAndDevice(
            @Param("employeeId") Long employeeId,
            @Param("deviceId") Long deviceId
    );

    // 🔁 OLD METHOD REQUIRED BY TESTS
    @Query("""
        SELECT COUNT(r) FROM IssuedDeviceRecord r
        WHERE r.employeeId = :employeeId
          AND r.status = 'ISSUED'
    """)
    long countActiveDevicesForEmployee(@Param("employeeId") Long employeeId);
}
