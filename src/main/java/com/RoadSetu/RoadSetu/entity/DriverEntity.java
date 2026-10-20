
package com.RoadSetu.RoadSetu.entity;

import com.RoadSetu.RoadSetu.enums.DriverStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "driver_details")
@Data
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String driverId;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private OwnerEntity owner;

    @OneToOne(mappedBy = "driver", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TruckEntity truck;

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
