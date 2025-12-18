package com.example.demo.service;

import java.util.List;
import com.example.demo.models.DeviceCatalogItem;

public interface DeviceCatalogService {

    DeviceCatalogItem createItem(DeviceCatalogItem item);

    DeviceCatalogItem updateActiveStatus(Long id, boolean active);

    List<DeviceCatalogItem> getAllItems();
}