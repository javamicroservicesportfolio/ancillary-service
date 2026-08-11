package com.portfolio.ancillaryservice.service;

import com.portfolio.payload.request.InsuranceCoverageRequest;
import com.portfolio.payload.response.InsuranceCoverageResponse;

import java.util.List;

public interface InsuranceCoverageService {
    InsuranceCoverageResponse createCoverage(InsuranceCoverageRequest coverageResponse);
    InsuranceCoverageResponse updateCoverage(Long coverageId, InsuranceCoverageRequest coverageRequest);
    void deleteCoverage(Long coverageId);
    InsuranceCoverageResponse getCoverageById(Long coverageId);
    List<InsuranceCoverageResponse> getCoverageByAncillaryId(Long ancillaryId);
    List<InsuranceCoverageResponse> getActiveCoverageByAncillaryId(Long ancillaryId);
    List<InsuranceCoverageResponse> getAllCoverages();
}
