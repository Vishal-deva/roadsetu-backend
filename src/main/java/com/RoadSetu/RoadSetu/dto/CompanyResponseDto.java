package com.RoadSetu.RoadSetu.dto;

import lombok.Data;

@Data

public class CompanyResponseDto {

    private String companyId;
    private String companyName;
    private String companyEmail;
    private String companyMobiileNumber;

    private String contactPersonName;
    private String designation;
    private String contactMobileNumber;
    private String contactEmail;

    private String fullAddress;
}
