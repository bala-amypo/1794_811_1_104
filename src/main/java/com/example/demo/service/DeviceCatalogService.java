package com.example.demo.service;
import com.example.demo.models.DeviceCatalogItem;
import java.util.List;

public interface DeviceCatalogService {
    DeviceCatalogItem createItem(DeviceCatalogItem item);
    DeviceCatalogItem updateActiveStatus(Long id, boolean active);
    List<DeviceCatalogItem> getAllItems();
}