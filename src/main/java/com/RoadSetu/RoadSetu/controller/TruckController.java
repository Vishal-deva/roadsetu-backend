package com.RoadSetu.RoadSetu.controller;


import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.dto.TruckDetailsDto;
import com.RoadSetu.RoadSetu.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class TruckController {

    @Autowired
    private TruckService truckService;

    @PostMapping("/savetruckdetails")
    private ResponseEntity<ResponseDto> saveTruckDetails(@RequestBody TruckDetailsDto truckDetailsDto)
    {
        return ResponseEntity.ok(truckService.saveTruckDetails(truckDetailsDto));
    }

    @GetMapping("/gettruckdetails")
    private ResponseEntity<TruckDetailsDto> getTruckDetails(@RequestParam String ownerId)
    {
        return  ResponseEntity.ok(truckService.getTruckDetails(ownerId));
    }
}
