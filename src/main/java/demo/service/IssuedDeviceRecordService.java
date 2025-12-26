package com.example.demo.service;

import com.example.demo.model.IssuedDeviceRecord;

public interface IssuedDeviceRecordService {

    IssuedDeviceRecord issuedDevice(IssuedDeviceRecord record);

    IssuedDeviceRecord returnDevice(Long recordId);
}
