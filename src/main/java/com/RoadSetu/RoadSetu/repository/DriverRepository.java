package com.RoadSetu.RoadSetu.repository;

import com.RoadSetu.RoadSetu.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<DriverEntity, String> {
    Optional<DriverEntity> findByDriverId(String driverId);
    List<DriverEntity> findAllByOwnerOwnerId(String ownerId);
}
