
package com.RoadSetu.RoadSetu.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "loads")
public class LoadEntity {

    @Id
    @Column(name = "load_id", nullable = false, updatable = false)
    private String loadId;

    @Column(name = "company_id", nullable = false)
    private String companyId;

    @Column(name = "load_type", nullable = false)
    private String loadType;

    @Column(name = "load_weight_tonnes", nullable = false, precision = 10, scale = 2)
    private BigDecimal loadWeightTonnes;

    @Column(name = "load_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal loadPrice;

    @Column(name = "address")
    private String address;

    @Column(name = "load_pickup_location", nullable = false)
    private String loadPickupLocation;

    @Column(name = "load_drop_location", nullable = false)
    private String loadDropLocation;

    @Column(name = "delivery_deadline")
    private LocalDateTime deliveryDeadline;

    @Column(name = "load_registration_timing", nullable = false)
    private LocalDateTime loadRegistrationTiming;

    @Column(name = "truck_type")
    private String truckType;

    @Column(name = "pickup_latitude", precision = 10, scale = 7)
    private BigDecimal pickupLatitude;

    @Column(name = "pickup_longitude", precision = 10, scale = 7)
    private BigDecimal pickupLongitude;

    @Column(name = "drop_latitude", precision = 10, scale = 7)
    private BigDecimal dropLatitude;

    @Column(name = "drop_longitude", precision = 10, scale = 7)
    private BigDecimal dropLongitude;

    @Column(name = "load_status", nullable = false)
    private String loadStatus;

    @PrePersist
    public void prePersist() {
        if (loadRegistrationTiming == null) {
            loadRegistrationTiming = LocalDateTime.now();
        }

        if (loadStatus == null) {
            loadStatus = "AVAILABLE";
        }
    }
}
