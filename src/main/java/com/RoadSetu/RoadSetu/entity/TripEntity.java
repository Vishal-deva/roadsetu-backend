package com.RoadSetu.RoadSetu.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "trip_details")
@Data
public class TripEntity {

    @Id
    @UuidGenerator
    private String tripId;

    private String fromLocation;
    private String toLocation;
    private String startDate;
    private String endDate;
    private String rentAmount;
    private String status;
    private int days;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;

    @ManyToOne
    @JoinColumn(name = "truck_id")
    private TruckEntity truck;
}