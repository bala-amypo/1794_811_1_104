package com.example.demo.service;

import com.example.demo.models.IssuedDeviceRecord;
import java.util.List;

public interface IssuedDeviceRecordService {

    IssuedDeviceRecord issueDevice(IssuedDeviceRecord record);

    IssuedDeviceRecord returnDevice(Long id);

    List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId);
}
