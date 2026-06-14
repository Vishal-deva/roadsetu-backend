package com.RoadSetu.RoadSetu.service;

import com.RoadSetu.RoadSetu.dto.OwnerDetailsDto;
import com.RoadSetu.RoadSetu.dto.OwnerResponseDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;

public interface OwnerService {
    OwnerResponseDto saveOwnerDetails(OwnerDetailsDto ownerDetailsDto);

    OwnerDetailsDto getOwnerDetails(String id);

    OwnerResponseDto login(OwnerDetailsDto ownerDetailsDto);
}
