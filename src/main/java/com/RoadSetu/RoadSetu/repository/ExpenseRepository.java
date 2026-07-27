package com.RoadSetu.RoadSetu.repository;

import com.RoadSetu.RoadSetu.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, String> {
    List<ExpenseEntity> findByTripTripId(String tripId);
}
