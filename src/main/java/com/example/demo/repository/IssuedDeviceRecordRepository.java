package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.EmployeeProfile;
import com.example.demo.models.IssuedDeviceRecord;

public interface IssuedDeviceRecordRepository
        extends JpaRepository<IssuedDeviceRecord, Long> {

    List<IssuedDeviceRecord> findByEmployee(EmployeeProfile employee);

    List<IssuedDeviceRecord> findByEmployeeAndActiveTrue(EmployeeProfile employee);
}
