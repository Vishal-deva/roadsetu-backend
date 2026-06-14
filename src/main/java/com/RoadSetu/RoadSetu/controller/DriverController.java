package com.RoadSetu.RoadSetu.controller;


import com.RoadSetu.RoadSetu.dto.DriverDetailsDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.service.DriverService;
import com.RoadSetu.RoadSetu.service.TruckService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/savedriverdetails")
    private ResponseEntity<ResponseDto>saveDriverDetails(@RequestBody DriverDetailsDto driverDetailsDto)
    {
        return ResponseEntity.ok(driverService.saveDriverDetails(driverDetailsDto));
    }

    @GetMapping("/getdriverdetails")
    private ResponseEntity<List<DriverDetailsDto>>getDriverDetails(@RequestParam String ownerId)
    {
        return ResponseEntity.ok(driverService.getDriverDetails(ownerId));
    }
}
