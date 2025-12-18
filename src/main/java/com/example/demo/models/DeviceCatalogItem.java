package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "device_catalog_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceCatalogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String deviceCode;

    private String deviceType;
    private String model;
    
    private Integer maxAllowedPerEmployee;
    
    private Boolean active;
}