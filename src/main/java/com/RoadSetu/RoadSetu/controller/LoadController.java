package com.RoadSetu.RoadSetu.controller;


import com.RoadSetu.RoadSetu.dto.CompanyDto;
import com.RoadSetu.RoadSetu.dto.LoadDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.service.CompanyService;
import com.RoadSetu.RoadSetu.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LoadController {


    @Autowired
    private LoadService loadService;

    @PostMapping("/saveloaddetails")
    public ResponseEntity<ResponseDto> saveLoadDetails(@RequestBody LoadDto loadDto)
    {
        return ResponseEntity.ok(loadService.saveLoadDetails(loadDto));
    }


    @GetMapping("/getloaddetails")
    public ResponseEntity<List<LoadDto>> getLoadByCompany(@RequestParam String companyId)
    {
        return ResponseEntity.ok(loadService.getLoadByCompany(companyId));
    }

    @GetMapping("/getallload")
    public ResponseEntity<List<LoadDto>> getAllLoad() {

        return ResponseEntity.ok(loadService.getAllLoad());
    }

}
