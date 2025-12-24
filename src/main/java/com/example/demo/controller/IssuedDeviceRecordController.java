package com.example.demo.controller;

import com.example.demo.models.IssuedDeviceRecord;
import com.example.demo.service.IssuedDeviceRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/issued-devices")
@Tag(name = "Issued Device Records Endpoints")
public class IssuedDeviceRecordController {
    private final IssuedDeviceRecordService service;

    public IssuedDeviceRecordController(IssuedDeviceRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<IssuedDeviceRecord> issue(@RequestBody IssuedDeviceRecord record) {
        return ResponseEntity.ok(service.issueDevice(record));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<IssuedDeviceRecord> returnDev(@PathVariable Long id) {
        return ResponseEntity.ok(service.returnDevice(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<IssuedDeviceRecord>> getByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(service.getIssuedDevicesByEmployee(employeeId));
    }
}