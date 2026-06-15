package com.RoadSetu.RoadSetu.controller;

import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.dto.TripDetailsDto;
import com.RoadSetu.RoadSetu.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping("/savetripdetails")
    private ResponseEntity<ResponseDto>saveTripDetails(@RequestBody TripDetailsDto tripDetailsDto)
    {
        return ResponseEntity.ok(tripService.saveTripDetails(tripDetailsDto));
    }

    @GetMapping("/gettripdetails")
    public ResponseEntity<List<TripDetailsDto>> gettripdetails(
            @RequestParam String ownerId,
            @RequestParam String truckId)
    {
        return ResponseEntity.ok(
                tripService.gettripdetails(ownerId, truckId));
    }
}
