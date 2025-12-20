package com.example.demo.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "issued_device_records")
public class IssuedDeviceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private Long deviceItemId;

    private LocalDate issuedDate;

    private LocalDate returnedDate;

    private String status; // ISSUED / RETURNED

    @PrePersist
    public void onIssue() {
        if (this.issuedDate == null) {
            this.issuedDate = LocalDate.now();
        }
        if (this.returnedDate == null) {
            this.status = "ISSUED";
        }
    }

    // ===== Getters and Setters =====

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getDeviceItemId() {
        return deviceItemId;
    }

    public void setDeviceItemId(Long deviceItemId) {
        this.deviceItemId = deviceItemId;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
        this.status = "RETURNED";
    }

    public String getStatus() {
        return status;
    }
}
