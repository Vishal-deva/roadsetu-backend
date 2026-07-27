package com.RoadSetu.RoadSetu.service;

import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.dto.TruckDetailsDto;

import java.util.List;

public interface TruckService {
    ResponseDto saveTruckDetails(TruckDetailsDto truckDetailsDto);

    List<TruckDetailsDto> getTruckDetails(String id);

    List<TruckDetailsDto> getAvailableTruck(String ownerId);
}
