package com.example.demo.service;

import com.example.demo.model.IssuedDeviceRecord;
import java.util.List;

public interface IssuedDeviceRecordService {

    // USED BY CONTROLLER
    IssuedDeviceRecord issueDevice(IssuedDeviceRecord record);

    IssuedDeviceRecord issuedDevice(IssuedDeviceRecord record);

    IssuedDeviceRecord returnDevice(Long recordId);

    List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId);
}
