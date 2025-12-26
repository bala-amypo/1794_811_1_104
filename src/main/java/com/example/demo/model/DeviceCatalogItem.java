package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "deviceCode"))
public class DeviceCatalogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceCode;
    private String deviceType;

    // ✅ REQUIRED BY TESTS
    private String model;

    private Integer maxAllowedPerEmployee;
    private Boolean active = true;

    /* ===== GETTERS & SETTERS ===== */

    public Long getId() {
        return id;
    }

    // ✅ REQUIRED BY TESTS
    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    // ✅ REQUIRED BY TESTS
    public String getModel() {
        return model;
    }

    // ✅ REQUIRED BY TESTS
    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMaxAllowedPerEmployee() {
        return maxAllowedPerEmployee;
    }

    public void setMaxAllowedPerEmployee(Integer maxAllowedPerEmployee) {
        this.maxAllowedPerEmployee = maxAllowedPerEmployee;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
