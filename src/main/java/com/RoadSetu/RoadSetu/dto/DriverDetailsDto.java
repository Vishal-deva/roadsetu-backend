package com.RoadSetu.RoadSetu.dto;


import lombok.Data;

@Data
public class DriverDetailsDto {

    private String driverId;
    private String ownerId;
    private String driverName;
    private String driverLicense;
    private String driverMobileNumber;
    private String driverNative;
    private String driverTruck;
    private String driverEmailId;
    private String password;

    private String truckId;
    private String truckNumber;
    private String responseMessage;
}
