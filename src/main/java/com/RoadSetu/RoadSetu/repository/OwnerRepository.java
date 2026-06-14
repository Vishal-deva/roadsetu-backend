package com.RoadSetu.RoadSetu.repository;

import com.RoadSetu.RoadSetu.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, String>{
}
