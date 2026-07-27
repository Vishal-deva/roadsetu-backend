package com.RoadSetu.RoadSetu.repository;

import com.RoadSetu.RoadSetu.entity.TruckEntity;
import com.RoadSetu.RoadSetu.enums.TruckStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TruckRepository extends JpaRepository<TruckEntity, String> {


    List<TruckEntity> findAllByOwnerOwnerId(String ownerId);

    List<TruckEntity> findAllByOwnerOwnerIdAndTruckStatus(String ownerId, TruckStatus truckStatus);
}
