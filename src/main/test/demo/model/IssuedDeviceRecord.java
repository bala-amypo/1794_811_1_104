package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "issued_device_records")
public class IssuedDeviceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private Long deviceId;
    private String status;
    private LocalDate issuedDate;
    private LocalDate returnedDate;

    public Long getId() { return id; }
    public Long getEmployeeId() { return employeeId; }
    public Long getDeviceId() { return deviceId; }
    public String getStatus() { return status; }
    public LocalDate getIssuedDate() { return issuedDate; }
    public LocalDate getReturnedDate() { return returnedDate; }

    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }
    public void setStatus(String status) { this.status = status; }
    public void setIssuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; }
    public void setReturnedDate(LocalDate returnedDate) { this.returnedDate = returnedDate; }
}
