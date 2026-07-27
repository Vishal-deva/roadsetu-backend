package com.RoadSetu.RoadSetu.entity;
import com.RoadSetu.RoadSetu.entity.DriverEntity;
import com.RoadSetu.RoadSetu.entity.OwnerEntity;
import com.RoadSetu.RoadSetu.entity.TripEntity;
import com.RoadSetu.RoadSetu.entity.TruckEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "expense_details")
@Data
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String expenseId;

    private String expenseDate;

    private String expenseType; // Fuel, Toll, Maintenance, Driver Salary, Fine, Food, Insurance, etc.

    private String expenseRemarks;

    private Double expenseCost;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;

    @ManyToOne
    @JoinColumn(name = "truck_id")
    private TruckEntity truck;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private TripEntity trip;
}