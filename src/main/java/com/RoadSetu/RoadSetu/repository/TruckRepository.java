package com.RoadSetu.RoadSetu.repository;

import com.RoadSetu.RoadSetu.entity.TruckEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TruckRepository extends JpaRepository<TruckEntity, String> {

    Optional<TruckEntity> findByOwnerOwnerId(String ownerId);
}
