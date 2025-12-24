package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.models.DeviceCatalogItem;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.service.DeviceCatalogService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceCatalogServiceImpl implements DeviceCatalogService {
    private final DeviceCatalogItemRepository repository;

    public DeviceCatalogServiceImpl(DeviceCatalogItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeviceCatalogItem createItem(DeviceCatalogItem item) {
        if (item.getMaxAllowedPerEmployee() != null && item.getMaxAllowedPerEmployee() < 1) {
            throw new BadRequestException("maxAllowedPerEmployee");
        }
        return repository.save(item);
    }

    @Override
    public DeviceCatalogItem updateActiveStatus(Long id, boolean active) {
        DeviceCatalogItem item = repository.findById(id).orElseThrow();
        item.setActive(active);
        return repository.save(item);
    }

    @Override
    public List<DeviceCatalogItem> getAllItems() {
        return repository.findAll();
    }
}