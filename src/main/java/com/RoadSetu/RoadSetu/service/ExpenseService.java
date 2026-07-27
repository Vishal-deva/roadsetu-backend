package com.RoadSetu.RoadSetu.service;

import com.RoadSetu.RoadSetu.dto.ExpenseDetailsDto;
import com.RoadSetu.RoadSetu.dto.ExpenseDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;

public interface ExpenseService {
    ResponseDto saveExpenseDetails(ExpenseDetailsDto expenseDetailsDto);

    ExpenseDto getExpenseByTrip(String tripId);
}
