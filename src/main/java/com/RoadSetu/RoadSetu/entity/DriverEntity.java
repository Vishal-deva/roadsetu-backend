package com.RoadSetu.RoadSetu.entity;

import com.RoadSetu.RoadSetu.enums.DriverStatus;
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
    @JoinColumn(name = "owner_id", nullable = false)
    private OwnerEntity owner;

    @Column(nullable = false)
    private String driverName;

    @Column(unique = true, nullable = false)
    private String driverLicense;

    @Column(unique = true, nullable = false)
    private String driverMobileNumber;

    private String driverNative;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DriverStatus driverStatus = DriverStatus.AVAILABLE;

    private String driverEmailId;
    private String password;
    private String role;
}