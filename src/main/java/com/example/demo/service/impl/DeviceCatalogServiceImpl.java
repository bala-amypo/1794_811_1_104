package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.DeviceCatalogItem;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.service.DeviceCatalogService;

@Service
public class DeviceCatalogServiceImpl implements DeviceCatalogService {

    @Autowired
    private DeviceCatalogItemRepository repo;

    @Override
    public DeviceCatalogItem createItem(DeviceCatalogItem item) {
        return repo.save(item);
    }

    @Override
    public DeviceCatalogItem updateActiveStatus(Long id, boolean active) {
        DeviceCatalogItem existingItem = repo.findById(id).orElse(null);
        if (existingItem != null) {
            existingItem.setActive(active); 
            return repo.save(existingItem);
        }
        return null;
    }

    @Override
    public List<DeviceCatalogItem> getAllItems() {
        return repo.findAll();
    }
}
