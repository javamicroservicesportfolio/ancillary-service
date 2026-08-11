package com.portfolio.ancillaryservice.repository;

import com.portfolio.ancillaryservice.model.InsuranceCoverage;
import com.portfolio.payload.response.InsuranceCoverageResponse;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceCoverageRepository extends JpaRepository<InsuranceCoverage, Long> {
    List<InsuranceCoverage> findByAncillaryId(Long ancillaryId);

    List<InsuranceCoverage> findByAncillaryIdAndActiveTrue(Long ancillaryId, boolean active);
}
