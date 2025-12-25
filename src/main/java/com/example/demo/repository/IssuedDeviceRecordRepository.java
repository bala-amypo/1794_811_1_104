package com.example.demo.repository;

import com.example.demo.models.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IssuedDeviceRecordRepository
        extends JpaRepository<IssuedDeviceRecord, Long> {

    // ✅ FIELD NAMES MUST MATCH ENTITY EXACTLY
    List<IssuedDeviceRecord> findByEmployeeIdAndDeviceIdAndStatus(
            Long employeeId,
            Long deviceId,
            String status
    );
}
