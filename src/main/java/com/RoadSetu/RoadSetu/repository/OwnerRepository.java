package com.RoadSetu.RoadSetu.repository;

import com.RoadSetu.RoadSetu.entity.OwnerEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, String>{

    Optional<OwnerEntity> findByOwnerEmailId(@Email(message = "Email Id is Required") String ownerEmailId);

    Optional<OwnerEntity> findByownerEmailId(@Email(message = "Email Id is Required") String ownerEmailId);
}
