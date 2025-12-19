package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;

@RestController
@RequestMapping("/api/devices")
public class DeviceCatalogController {

    @Autowired
    private DeviceCatalogService deviceCatalogService;

   
    @PostMapping
    public DeviceCatalogItem createDevice(@RequestBody DeviceCatalogItem item) {
        return deviceCatalogService.createItem(item);
    }

   
    @PutMapping("/{id}/active")
    public DeviceCatalogItem updateActiveStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return deviceCatalogService.updateActiveStatus(id, active);
    }

   
    @GetMapping
    public List<DeviceCatalogItem> getAllDevices() {
        return deviceCatalogService.getAllItems();
    }
}
