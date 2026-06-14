package com.RoadSetu.RoadSetu.service;

import com.RoadSetu.RoadSetu.dto.DriverDetailsDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;

import java.util.List;

public interface DriverService {
    ResponseDto saveDriverDetails(DriverDetailsDto driverDetailsDto);

    List<DriverDetailsDto> getDriverDetails(String ownerId);
}
