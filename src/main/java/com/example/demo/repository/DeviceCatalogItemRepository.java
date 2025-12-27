package com.example.demo.repository;

import com.example.demo.model.DeviceCatalogItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceCatalogItemRepository
        extends JpaRepository<DeviceCatalogItem, Long> {

    // ✅ REQUIRED for your tests
    Optional<DeviceCatalogItem> findByDeviceCode(String deviceCode);
}




































//package com.example.demo.repository;

//import com.example.demo.model.DeviceCatalogItem;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

//@Repository
//public interface DeviceCatalogItemRepository
  //     extends JpaRepository<DeviceCatalogItem, Long> {

    
//}

