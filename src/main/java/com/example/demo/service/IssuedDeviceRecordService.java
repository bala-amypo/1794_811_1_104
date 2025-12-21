package com.example.demo.service;

import com.example.demo.models.IssuedDeviceRecord;

public interface IssuedDeviceRecordService {

    IssuedDeviceRecord issueDevice(IssuedDeviceRecord record);

    IssuedDeviceRecord returnDevice(Long recordId);
}
