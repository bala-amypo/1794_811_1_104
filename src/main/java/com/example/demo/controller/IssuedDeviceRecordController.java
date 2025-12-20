package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.IssuedDeviceRecord;
import com.example.demo.service.IssuedDeviceRecordService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/issued-devices")
@Tag(name = "Issued Devices")
public class IssuedDeviceRecordController {

    private final IssuedDeviceRecordService issuedService;

    public IssuedDeviceRecordController(IssuedDeviceRecordService issuedService) {
        this.issuedService = issuedService;
    }

    @PostMapping
    public IssuedDeviceRecord issue(@RequestBody IssuedDeviceRecord record) {
        return issuedService.issueDevice(record);
    }

    @PutMapping("/{id}/return")
    public IssuedDeviceRecord returnDevice(@PathVariable Long id) {
        return issuedService.returnDevice(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<IssuedDeviceRecord> getByEmployee(@PathVariable Long employeeId) {
        return issuedService.getIssuedDevicesByEmployee(employeeId);
    }
}
