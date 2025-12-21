package com.example.demo.service;

import com.example.demo.models.IssuedDeviceRecord;

public interface IssuedDeviceRecordService {

    IssuedDeviceRecord issuedDevice(IssuedDeviceRecord record);

    IssuedDeviceRecord returnDevice(Long recordId);
}
