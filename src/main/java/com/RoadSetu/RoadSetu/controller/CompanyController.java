package com.RoadSetu.RoadSetu.controller;


import com.RoadSetu.RoadSetu.dto.CompanyDto;
import com.RoadSetu.RoadSetu.dto.CompanyResponseDto;
import com.RoadSetu.RoadSetu.dto.DriverDetailsDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.entity.CompanyEntity;
import com.RoadSetu.RoadSetu.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyController {


    @Autowired
    private CompanyService companyService;

    @PostMapping("/savecompanydetails")
    public ResponseEntity<ResponseDto> saveCompanyDetails(@RequestBody CompanyDto companyDto)
    {
        return ResponseEntity.ok(companyService.saveCompanyDetails(companyDto));
    }

    @GetMapping("/getcompanydetails")
    public ResponseEntity<CompanyResponseDto> getCompanyDetails(@RequestParam("companyId") String companyId)
    {
        return ResponseEntity.ok(companyService.getCompanyDetails(companyId));
    }

}
