package com.RoadSetu.RoadSetu.controller;

import com.RoadSetu.RoadSetu.dto.OwnerDetailsDto;
import com.RoadSetu.RoadSetu.dto.OwnerResponseDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/saveownerdetails")
    public ResponseEntity<OwnerResponseDto> saveOwnerDetails(@Valid @RequestBody OwnerDetailsDto ownerDetailsDto)
    {
        return ResponseEntity.ok(ownerService.saveOwnerDetails(ownerDetailsDto));
    }

    @GetMapping("/getprofiledetails")
    public ResponseEntity<OwnerDetailsDto> getOwnerDetails(@RequestParam String id)
    {
        return ResponseEntity.ok(ownerService.getOwnerDetails(id));
    }

}
