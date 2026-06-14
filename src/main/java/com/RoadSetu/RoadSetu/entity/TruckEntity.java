package com.RoadSetu.RoadSetu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TruckEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String truckId;

    private String truckNumber;      // TN 33 AB 1234

    private String truckName;

    private Double tonCapacity;

    private String rcNumber;

    private String truckType;        // Container, Open Body, Tanker

    private String fuelType;         // Diesel, CNG

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;

    private Boolean available;

    private String currentLocation;
}
