package com.RoadSetu.RoadSetu.dto;


import lombok.Data;

@Data
public class ResponseDto {

    private int statusCode;
    private String message;



    private String driverId;
    private String driverName;
    private String truckId;
    private String ownerId;

    private String role;
}
