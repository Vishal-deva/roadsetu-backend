package com.RoadSetu.RoadSetu.service;

import com.RoadSetu.RoadSetu.dto.OwnerDetailsDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;

public interface OwnerService {
    ResponseDto saveOwnerDetails(OwnerDetailsDto ownerDetailsDto);

    OwnerDetailsDto getOwnerDetails(String id);
}
