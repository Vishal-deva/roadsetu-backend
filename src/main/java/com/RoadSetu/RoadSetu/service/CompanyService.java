package com.RoadSetu.RoadSetu.service;

import com.RoadSetu.RoadSetu.dto.CompanyDto;
import com.RoadSetu.RoadSetu.dto.CompanyResponseDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;

import java.util.List;

public interface CompanyService {
    ResponseDto saveCompanyDetails(CompanyDto companyDto);
    CompanyResponseDto getCompanyDetails(String companyId);
}
