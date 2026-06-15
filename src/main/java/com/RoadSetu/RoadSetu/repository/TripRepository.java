package com.RoadSetu.RoadSetu.repository;

import com.RoadSetu.RoadSetu.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<TripEntity, String> {


    List<TripEntity> findByOwner_OwnerIdAndTruck_TruckId(
            String ownerId,
            String truckId
    );
}

