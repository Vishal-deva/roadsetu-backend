package com.RoadSetu.RoadSetu.dto;

import lombok.Data;

import java.util.List;


@Data
public class ExpenseDto {


    private Double totalExpense;

    private List<ExpenseDetailsDto> expense ;
}
