package com.RoadSetu.RoadSetu.service;

import com.RoadSetu.RoadSetu.dto.LoadDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;

import java.util.List;

public interface LoadService {
    ResponseDto saveLoadDetails(LoadDto loadDto);

    List<LoadDto> getLoadByCompany(String companyId);

    List<LoadDto> getAllLoad();
}
