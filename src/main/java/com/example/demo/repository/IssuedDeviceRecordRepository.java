package com.example.demo.repository;

import com.example.demo.models.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // <-- IMPORTANT: Must be this exact import
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuedDeviceRecordRepository extends JpaRepository<IssuedDeviceRecord, Long> {

    // The @Query annotation stops Spring from "guessing" the name.
    // Ensure 'i.employee.id' matches the field name in your Model.
    @Query("SELECT COUNT(i) FROM IssuedDeviceRecord i WHERE i.employee.id = :employeeId AND i.returnDate IS NULL")
    int countActiveDevicesForEmployee(@Param("employeeId") Long employeeId);
}