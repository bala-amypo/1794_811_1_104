package com.example.demo.service.impl;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.repository.DeviceCatalogItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceCatalogServiceImpl {

    private final DeviceCatalogItemRepository repository;

    public DeviceCatalogServiceImpl(DeviceCatalogItemRepository repository) {
        this.repository = repository;
    }

    public DeviceCatalogItem save(DeviceCatalogItem item) {
        return repository.save(item);
    }

    public List<DeviceCatalogItem> findAll() {
        return repository.findAll();
    }
}
