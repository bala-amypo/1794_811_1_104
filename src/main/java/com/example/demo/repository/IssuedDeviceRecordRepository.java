package com.example.demo.repository;

import com.example.demo.models.IssuedDeviceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuedDeviceRecordRepository extends JpaRepository<IssuedDeviceRecord, Long> {

    // This is the fix. We define the query manually.
    // We count records for the employee where the returnDate is still null (meaning they still have it).
    @Query("SELECT COUNT(i) FROM IssuedDeviceRecord i WHERE i.employee.id = :employeeId AND i.returnDate IS NULL")
    int countActiveDevicesForEmployee(@Param("employeeId") Long employeeId);

    List<IssuedDeviceRecord> findByEmployeeId(Long employeeId);
}