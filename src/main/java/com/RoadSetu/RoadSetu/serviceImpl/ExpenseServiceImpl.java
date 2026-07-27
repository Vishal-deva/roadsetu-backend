package com.RoadSetu.RoadSetu.serviceImpl;

import com.RoadSetu.RoadSetu.dto.ExpenseDetailsDto;
import com.RoadSetu.RoadSetu.dto.ExpenseDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.entity.*;
import com.RoadSetu.RoadSetu.repository.*;
import com.RoadSetu.RoadSetu.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private TripRepository tripRepository;


    @Override
    public ResponseDto saveExpenseDetails(ExpenseDetailsDto expenseDetailsDto) {

        ResponseDto responseDto = new ResponseDto();

        try {

            // Fetch related entities
            OwnerEntity owner = ownerRepository.findById(expenseDetailsDto.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));

            TruckEntity truck = truckRepository.findById(expenseDetailsDto.getTruckId())
                    .orElseThrow(() -> new RuntimeException("Truck not found"));

            DriverEntity driver = driverRepository.findById(expenseDetailsDto.getDriverId())
                    .orElseThrow(() -> new RuntimeException("Driver not found"));
            TripEntity trip = tripRepository.findById(expenseDetailsDto.getTripId())
                    .orElseThrow(() -> new RuntimeException("Trip not found"));
            ExpenseEntity expenseEntity;
            // CREATE
            if (expenseDetailsDto.getExpenseId() == null ||
                    expenseDetailsDto.getExpenseId().isEmpty()) {
                expenseEntity = new ExpenseEntity();
            }
            // UPDATE
            else {
                expenseEntity = expenseRepository.findById(expenseDetailsDto.getExpenseId())
                        .orElseThrow(() -> new RuntimeException("Expense not found"));
            }
            // Common fields for save and update
            expenseEntity.setOwner(owner);
            expenseEntity.setTruck(truck);
            expenseEntity.setDriver(driver);
            expenseEntity.setTrip(trip);
            expenseEntity.setExpenseType(expenseDetailsDto.getExpenseType());
            expenseEntity.setExpenseAmount(expenseDetailsDto.getExpenseAmount());
            expenseEntity.setExpenseDate(
                    LocalDate.parse(expenseDetailsDto.getExpenseDate())
            );
            expenseEntity.setLocation(expenseDetailsDto.getLocation());
            expenseEntity.setRemarks(expenseDetailsDto.getRemarks());
            expenseRepository.save(expenseEntity);
            if (expenseDetailsDto.getExpenseId() == null ||
                    expenseDetailsDto.getExpenseId().isEmpty()) {
                responseDto.setMessage("Expense saved successfully");
            } else {
                responseDto.setMessage("Expense updated successfully");
            }
            responseDto.setStatusCode(200);
        } catch (Exception e) {
            responseDto.setStatusCode(500);
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }

    @Override
    public ExpenseDto getExpenseByTrip(String tripId) {

        ExpenseDto expenseDto = new ExpenseDto();

        try {

            if (tripId == null || tripId.isEmpty()) {
                throw new RuntimeException("Trip Id is required");
            }


            List<ExpenseEntity> expenseList =
                    expenseRepository.findByTripTripId(tripId);


            if (expenseList.isEmpty()) {
                throw new RuntimeException("No expense found for this trip");
            }


            List<ExpenseDetailsDto> expenseDetailsList = new ArrayList<>();

            Double totalExpense = 0.0;


            for (ExpenseEntity expenseEntity : expenseList) {
                ExpenseDetailsDto expenseDetailsDto = new ExpenseDetailsDto();

                expenseDetailsDto.setExpenseId(expenseEntity.getExpenseId());
                expenseDetailsDto.setOwnerId(expenseEntity.getOwner().getOwnerId());
                expenseDetailsDto.setTruckId(expenseEntity.getTruck().getTruckId());

                expenseDetailsDto.setDriverId(expenseEntity.getDriver().getDriverId());

                expenseDetailsDto.setTripId(expenseEntity.getTrip().getTripId());

                expenseDetailsDto.setExpenseType(expenseEntity.getExpenseType());

                expenseDetailsDto.setExpenseAmount(expenseEntity.getExpenseAmount());

                expenseDetailsDto.setExpenseDate(expenseEntity.getExpenseDate().toString());

                expenseDetailsDto.setLocation(expenseEntity.getLocation());

                expenseDetailsDto.setRemarks(expenseEntity.getRemarks());

                expenseDetailsList.add(expenseDetailsDto);

                totalExpense = totalExpense + expenseEntity.getExpenseAmount();
            }

            expenseDto.setTotalExpense(totalExpense);
            expenseDto.setExpense(expenseDetailsList);

        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());

        }
        return expenseDto;
    }
}
