package com.example.demo.dto;

public class IssuedDeviceRequest {

    private Long employeeId;
    private Long deviceItemId;

    public IssuedDeviceRequest() {
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
}
