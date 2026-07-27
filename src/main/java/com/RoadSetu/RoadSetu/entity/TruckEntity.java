package com.RoadSetu.RoadSetu.entity;

import com.RoadSetu.RoadSetu.enums.TruckStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "truck_details")
@Data
public class TruckEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String truckId;

    // Basic Details
    @Column(nullable = false, unique = true)
    private String truckNumber;          // TN33AB1234

    @Column(nullable = false)
    private String truckName;

    private Double tonCapacity;

    @Column(nullable = false, unique = true)
    private String rcNumber;

    private String truckType;            // Container, Open Body, Tanker
    private String fuelType;             // Diesel, CNG

    // Truck Status
    @Enumerated(EnumType.STRING)
    @Column(name="truck_status", nullable = false)
    private TruckStatus truckStatus  = TruckStatus.AVAILABLE;

    private String currentLocation;

    // Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private OwnerEntity owner;

    // Assigned Driver (Optional)
    @OneToOne
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;
}