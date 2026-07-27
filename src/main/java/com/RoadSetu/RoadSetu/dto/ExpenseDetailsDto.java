package com.RoadSetu.RoadSetu.dto;

import com.RoadSetu.RoadSetu.enums.ExpenseType;
import lombok.Data;

@Data
public class ExpenseDetailsDto {

    private String expenseId;

    private String ownerId;

    private String truckId;

    private String driverId;

    private String tripId;

    private ExpenseType expenseType;

    private Double expenseAmount;

    private String expenseDate;

    private String location;

    private String remarks;



}