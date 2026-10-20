
package com.RoadSetu.RoadSetu.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LoadDto {

    private String loadId;

    private String companyId;

    private String companyName;

    private String loadType;

    private BigDecimal loadWeightTonnes;

    private BigDecimal loadPrice;

    private String address;

    private String loadPickupLocation;

    private String loadDropLocation;

    private LocalDateTime deliveryDeadline;

    private LocalDateTime loadRegistrationTiming;

    private String truckType;

    private String pickupLatitude;

    private String pickupLongitude;

    private String dropLatitude;

    private String dropLongitude;

    private String loadStatus;
}
