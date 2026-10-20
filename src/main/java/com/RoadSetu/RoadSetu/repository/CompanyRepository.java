
package com.RoadSetu.RoadSetu.repository;

import com.RoadSetu.RoadSetu.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository
        extends JpaRepository<CompanyEntity, String> {
}
