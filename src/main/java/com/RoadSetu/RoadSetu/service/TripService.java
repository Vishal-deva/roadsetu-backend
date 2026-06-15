package com.RoadSetu.RoadSetu.service;

import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.dto.TripDetailsDto;

import java.util.List;

public interface TripService {

    ResponseDto saveTripDetails(TripDetailsDto tripDetailsDto);

    List<TripDetailsDto> gettripdetails(String ownerId, String truckId);
}