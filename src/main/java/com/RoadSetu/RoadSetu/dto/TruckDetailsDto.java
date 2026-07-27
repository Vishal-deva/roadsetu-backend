package com.RoadSetu.RoadSetu.dto;

import com.RoadSetu.RoadSetu.enums.TruckStatus;
import lombok.Data;

@Data
public class TruckDetailsDto {

    private String truckId;

    private String truckName;

    private String truckNumber;

    private Double toneCapacity;

    private String rcNumber;

    private String truckType;

    private String fuelType;

    private TruckStatus truckStatus;

    private String currentLocation;

    private String ownerId;

    private String driverId;

    private String driverName;

    private String driverMobileNumber;
}