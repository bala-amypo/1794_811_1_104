package com.example.demo.service;

import com.example.demo.models.IssuedDeviceRecord;
import java.util.List;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;


public interface IssuedDeviceRecordService {

    IssuedDeviceRecord issueDevice(IssuedDeviceRecord record);

    IssuedDeviceRecord returnDevice(Long id);

    List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId);
}
