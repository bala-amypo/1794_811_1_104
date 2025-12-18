package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/devices")
@Tag(name = "Device Catalog")
public class DeviceCatalogController {

    private final DeviceCatalogService deviceService;

    public DeviceCatalogController(DeviceCatalogService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/")
    public DeviceCatalogItem createItem(@RequestBody DeviceCatalogItem item) {
        return deviceService.createItem(item);
    }

    @PutMapping("/{id}/active")
    public DeviceCatalogItem updateActiveStatus(@PathVariable Long id, @RequestParam boolean active) {
        return deviceService.updateActiveStatus(id, active);
    }

    @GetMapping("/")
    public List<DeviceCatalogItem> getAllItems() {
        return deviceService.getAllItems();
    }
}