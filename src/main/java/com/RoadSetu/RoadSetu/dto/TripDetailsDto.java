package com.RoadSetu.RoadSetu.dto;

import lombok.Data;

@Data
public class TripDetailsDto {

    private String tripId;

    private String fromLocation;

    private String toLocation;

    private String startDate;

    private String endDate;

    private String rentAmount;

    private String status;

    private String driverName;

    private String driverId;

    private String truckName;

    private String truckId;

    private String ownerId;

    private Integer days;
}