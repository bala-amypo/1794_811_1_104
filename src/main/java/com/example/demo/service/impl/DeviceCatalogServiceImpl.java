package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.DeviceCatalogItem;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.service.DeviceCatalogService;

@Service
public class DeviceCatalogServiceImpl implements DeviceCatalogService {

    private final DeviceCatalogItemRepository repo;

    public DeviceCatalogServiceImpl(DeviceCatalogItemRepository repo) {
        this.repo = repo;
    }

    @Override
    public DeviceCatalogItem createItem(DeviceCatalogItem item) {
        if (item.getMaxAllowedPerEmployee() == null || item.getMaxAllowedPerEmployee() < 1) {
            throw new BadRequestException("Invalid limit: maxAllowedPerEmployee");
        }
        return repo.save(item);
    }

    @Override
    public DeviceCatalogItem updateActiveStatus(Long id, boolean active) {
        DeviceCatalogItem existingItem = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device item not found"));
        existingItem.setActive(active);
        return repo.save(existingItem);
    }

    @Override
    public List<DeviceCatalogItem> getAllItems() {
        return repo.findAll();
    }
}