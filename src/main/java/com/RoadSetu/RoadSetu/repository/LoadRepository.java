package com.RoadSetu.RoadSetu.repository;

import com.RoadSetu.RoadSetu.entity.LoadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoadRepository extends JpaRepository<LoadEntity, String> {
    List<LoadEntity> findByCompanyId(String companyId);
}
