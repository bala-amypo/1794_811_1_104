package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.EligibilityCheckRecord;

@Repository
public interface EligibilityCheckRecordRepository
        extends JpaRepository<EligibilityCheckRecord, Long> {

    // REQUIRED BY TEST CASE
    List<EligibilityCheckRecord> findByEmployeeId(Long employeeId);
}
