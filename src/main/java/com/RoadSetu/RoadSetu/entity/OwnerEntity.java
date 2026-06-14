package com.RoadSetu.RoadSetu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ownerId;

    private String ownerName;

    private String ownerEmailId;

    private String ownerMobileNumber;

    private String ownerPassword;

    private String companyName;
}