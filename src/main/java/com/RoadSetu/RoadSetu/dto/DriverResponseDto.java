package com.RoadSetu.RoadSetu.dto;


import lombok.Data;

@Data
public class DriverResponseDto {
        private String driverId;

        private String role;

        private String message;

        private int statusCode;
}
