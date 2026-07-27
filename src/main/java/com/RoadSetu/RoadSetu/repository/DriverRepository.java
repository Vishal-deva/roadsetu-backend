package com.RoadSetu.RoadSetu.repository;

import com.RoadSetu.RoadSetu.entity.DriverEntity;
import com.RoadSetu.RoadSetu.enums.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<DriverEntity, String> {
    Optional<DriverEntity> findByDriverId(String driverId);
    List<DriverEntity> findAllByOwnerOwnerId(String ownerId);
    List<DriverEntity> findAllByOwnerOwnerIdAndDriverStatus(
            String ownerId,
            DriverStatus driverStatus);

    Optional<DriverEntity> findByDriverEmailId(String driverEmailId);

}
