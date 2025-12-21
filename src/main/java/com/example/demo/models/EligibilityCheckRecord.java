package com.example.demo.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class EligibilityCheckRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private Long deviceItemId;
    private Boolean isEligible;
    private String reason;
    private LocalDateTime checkedAt;

    @PrePersist
    void onCheck() {
        checkedAt = LocalDateTime.now();
    }

    public void setEmployeeId(Long employeeId) { 
    this.employeeId = employeeId;
    }
    public void setDeviceItemId(Long deviceItemId) {
    this.deviceItemId = deviceItemId; 
    }
    public void setIsEligible(Boolean isEligible) { 
    this.isEligible = isEligible; 
    }
    public void setReason(String reason) { this.reason = reason; }
}
