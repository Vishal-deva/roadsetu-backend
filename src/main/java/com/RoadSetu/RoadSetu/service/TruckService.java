package com.RoadSetu.RoadSetu.service;

import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.dto.TruckDetailsDto;

public interface TruckService {
    ResponseDto saveTruckDetails(TruckDetailsDto truckDetailsDto);

    TruckDetailsDto getTruckDetails(String id);
}
