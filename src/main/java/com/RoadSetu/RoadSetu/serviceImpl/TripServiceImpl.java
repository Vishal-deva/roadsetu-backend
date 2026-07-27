package com.RoadSetu.RoadSetu.serviceImpl;

import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.dto.TripDetailsDto;
import com.RoadSetu.RoadSetu.entity.DriverEntity;
import com.RoadSetu.RoadSetu.entity.OwnerEntity;
import com.RoadSetu.RoadSetu.entity.TripEntity;
import com.RoadSetu.RoadSetu.entity.TruckEntity;
import com.RoadSetu.RoadSetu.repository.DriverRepository;
import com.RoadSetu.RoadSetu.repository.OwnerRepository;
import com.RoadSetu.RoadSetu.repository.TripRepository;
import com.RoadSetu.RoadSetu.repository.TruckRepository;
import com.RoadSetu.RoadSetu.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private TruckRepository truckRepository;

    @Override
    public ResponseDto saveTripDetails(TripDetailsDto tripDetailsDto) {

        ResponseDto responseDto = new ResponseDto();

        try {

            // CREATE TRIP
            if (tripDetailsDto.getTripId() == null ||
                    tripDetailsDto.getTripId().isEmpty()) {

                if (tripDetailsDto.getOwnerId() == null ||
                        tripDetailsDto.getTruckId() == null ||
                        tripDetailsDto.getDriverId() == null) {

                    responseDto.setStatusCode(400);
                    responseDto.setMessage("Owner Id, Truck Id and Driver Id are required");
                    return responseDto;
                }

                OwnerEntity owner = ownerRepository
                        .findById(tripDetailsDto.getOwnerId())
                        .orElseThrow(() ->
                                new RuntimeException("Owner Not Found"));

                DriverEntity driver = driverRepository
                        .findById(tripDetailsDto.getDriverId())
                        .orElseThrow(() ->
                                new RuntimeException("Driver Not Found"));

                TruckEntity truck = truckRepository
                        .findById(tripDetailsDto.getTruckId())
                        .orElseThrow(() ->
                                new RuntimeException("Truck Not Found"));

                TripEntity tripEntity = new TripEntity();

                tripEntity.setOwner(owner);
                tripEntity.setDriver(driver);
                tripEntity.setTruck(truck);

                tripEntity.setFromLocation(tripDetailsDto.getFromLocation());
                tripEntity.setToLocation(tripDetailsDto.getToLocation());
                tripEntity.setRentAmount(tripDetailsDto.getRentAmount());
                tripEntity.setStartDate(tripDetailsDto.getStartDate());
                tripEntity.setStatus("ON_ROUTE");

                tripRepository.save(tripEntity);

                responseDto.setStatusCode(200);
                responseDto.setMessage("Trip Started Safe Journey!");

                return responseDto;
            }

            // UPDATE TRIP
            Optional<TripEntity> optEntity =
                    tripRepository.findById(tripDetailsDto.getTripId());

            if (optEntity.isPresent()) {

                TripEntity tripEntity = optEntity.get();

                if ("COMPLETED".equalsIgnoreCase(tripDetailsDto.getStatus())) {

                    if (tripDetailsDto.getEndDate() == null ||
                            tripDetailsDto.getEndDate().isEmpty()) {

                        responseDto.setStatusCode(400);
                        responseDto.setMessage("End Date is required");
                        return responseDto;
                    }

                    tripEntity.setEndDate(tripDetailsDto.getEndDate());
                    tripEntity.setStatus("COMPLETED");

                    LocalDate startDate =
                            LocalDate.parse(tripEntity.getStartDate());

                    LocalDate endDate =
                            LocalDate.parse(tripDetailsDto.getEndDate());

                    long totalDays =
                            ChronoUnit.DAYS.between(startDate, endDate) + 1;

                    tripEntity.setDays(Integer.valueOf((int) totalDays));

                    tripRepository.save(tripEntity);

                    responseDto.setStatusCode(200);
                    responseDto.setMessage("Trip Completed!");
                } else {

                    responseDto.setStatusCode(400);
                    responseDto.setMessage("Invalid Status");
                }

            } else {

                responseDto.setStatusCode(404);
                responseDto.setMessage("Trip Not Found");
            }

        } catch (Exception e) {

            responseDto.setStatusCode(500);
            responseDto.setMessage(e.getMessage());
        }

        return responseDto;
    }

    @Override
    public List<TripDetailsDto> gettripdetails(String ownerId, String truckId) {

        try {

            List<TripEntity> tripEntities =
                    tripRepository.findByOwner_OwnerIdAndTruck_TruckId(
                            ownerId,
                            truckId
                    );

            List<TripDetailsDto> tripDetailsDtos = new ArrayList<>();

            for (TripEntity tripEntity : tripEntities) {

                TripDetailsDto tripDetailsDto = new TripDetailsDto();

                tripDetailsDto.setTripId(tripEntity.getTripId());
                tripDetailsDto.setOwnerId(tripEntity.getOwner().getOwnerId());
                tripDetailsDto.setDriverId(tripEntity.getDriver().getDriverId());
                tripDetailsDto.setTruckId(tripEntity.getTruck().getTruckId());
                tripDetailsDto.setDriverId(tripEntity.getDriver().getDriverName());
                tripDetailsDto.setDriverName(tripEntity.getDriver().getDriverMobileNumber());
                tripDetailsDto.setFromLocation(tripEntity.getFromLocation());
                tripDetailsDto.setToLocation(tripEntity.getToLocation());
                tripDetailsDto.setStatus(tripEntity.getStatus());
                tripDetailsDto.setStartDate(tripEntity.getStartDate());
                tripDetailsDto.setEndDate(tripEntity.getEndDate());
                tripDetailsDto.setRentAmount(tripEntity.getRentAmount());
                tripDetailsDto.setDays(tripEntity.getDays());

                tripDetailsDtos.add(tripDetailsDto);
            }

            return tripDetailsDtos;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}