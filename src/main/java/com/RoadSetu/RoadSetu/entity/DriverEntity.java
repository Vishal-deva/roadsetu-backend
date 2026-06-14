package com.RoadSetu.RoadSetu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "driver_details")
@Data
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String driverId;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;

    @Column(nullable = false)
    private String driverName;

    @Column(unique = true, nullable = false)
    private String driverLicense;

    @Column(unique = true, nullable = false)
    private String driverMobileNumber;

    private String driverNative;

    private String driverTruck;
}