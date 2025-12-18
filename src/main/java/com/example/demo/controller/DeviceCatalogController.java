package com.example.demo.controller;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@Tag(name = "Device Catalog Controller", description = "Management of device inventory items")
public class DeviceCatalogItemController {

    private final DeviceCatalogService deviceService;

    // Strict Requirement: Constructor Injection
    public DeviceCatalogItemController(DeviceCatalogService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    @Operation(summary = "Create device catalog item")
    public ResponseEntity<DeviceCatalogItem> createItem(@RequestBody DeviceCatalogItem item) {
        return ResponseEntity.ok(deviceService.createItem(item));
    }

    @PutMapping("/{id}/active")
    @Operation(summary = "Activate/deactivate item")
    public ResponseEntity<DeviceCatalogItem> updateActiveStatus(
            @PathVariable Long id, 
            @RequestParam boolean active) {
        return ResponseEntity.ok(deviceService.updateActiveStatus(id, active));
    }

    @GetMapping
    @Operation(summary = "List all device items")
    public ResponseEntity<List<DeviceCatalogItem>> getAllItems() {
        return ResponseEntity.ok(deviceService.getAllItems());
    }
}