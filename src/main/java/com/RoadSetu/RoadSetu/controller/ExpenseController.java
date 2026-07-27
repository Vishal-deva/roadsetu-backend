package com.RoadSetu.RoadSetu.controller;


import com.RoadSetu.RoadSetu.dto.ExpenseDetailsDto;
import com.RoadSetu.RoadSetu.dto.ExpenseDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;


    @PostMapping("/saveexpense")
    public ResponseEntity<ResponseDto> saveExpenseDetails (@RequestBody ExpenseDetailsDto expenseDetailsDto)
    {
        return ResponseEntity.ok(expenseService.saveExpenseDetails(expenseDetailsDto));
    }

    @GetMapping("/getexpensebytrip")
    public  ResponseEntity<ExpenseDto> getExpenseByTrip (@RequestParam String tripId)
    {
        return  ResponseEntity.ok(expenseService.getExpenseByTrip(tripId));
    }

}
