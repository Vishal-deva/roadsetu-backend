package com.RoadSetu.RoadSetu.dto;


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
    private boolean isAvailable;
    private String currentLocation;
    private String ownerId;




    private String driverName1;
    private String driver1MobileNumber;
    private String driverName2;
    private String driver2MobileNumber;

}
