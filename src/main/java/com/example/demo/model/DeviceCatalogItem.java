package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "device_catalog_items")
public class DeviceCatalogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceCode;
    private String deviceType;
    private Integer maxAllowedPerEmployee;
    private Boolean active = true;

    public Long getId() { return id; }
    public String getDeviceCode() { return deviceCode; }
    public String getDeviceType() { return deviceType; }
    public Integer getMaxAllowedPerEmployee() { return maxAllowedPerEmployee; }
    public Boolean getActive() { return active; }

    public void setDeviceCode(String deviceCode) { this.deviceCode = deviceCode; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
    public void setMaxAllowedPerEmployee(Integer maxAllowedPerEmployee) {
        this.maxAllowedPerEmployee = maxAllowedPerEmployee;
    }
    public void setActive(Boolean active) { this.active = active; }
}
