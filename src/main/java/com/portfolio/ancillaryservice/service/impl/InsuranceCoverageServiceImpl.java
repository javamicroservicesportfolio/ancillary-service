package com.portfolio.ancillaryservice.service.impl;

import com.portfolio.ancillaryservice.mapper.InsuranceCoverageMapper;
import com.portfolio.ancillaryservice.model.Ancillary;
import com.portfolio.ancillaryservice.model.InsuranceCoverage;
import com.portfolio.ancillaryservice.repository.AncillaryRepository;
import com.portfolio.ancillaryservice.repository.InsuranceCoverageRepository;
import com.portfolio.ancillaryservice.service.InsuranceCoverageService;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.payload.request.InsuranceCoverageRequest;
import com.portfolio.payload.response.InsuranceCoverageResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InsuranceCoverageServiceImpl implements InsuranceCoverageService {
    private final AncillaryRepository ancillaryRepository;
    private final InsuranceCoverageRepository insuranceCoverageRepository;

    @Override
    public InsuranceCoverageResponse createCoverage(InsuranceCoverageRequest coverageResponse) {
        Ancillary ancillary = ancillaryRepository.findById(coverageResponse.getAncillaryId())
                .orElseThrow(() -> new ResourceNotFoundException("Ancillary not found"));

        InsuranceCoverage insuranceCoverage = InsuranceCoverageMapper.toEntity(coverageResponse, ancillary);
        insuranceCoverageRepository.save(insuranceCoverage);

        return InsuranceCoverageMapper.toResponse(insuranceCoverage);
    }

    @Override
    public InsuranceCoverageResponse updateCoverage(Long coverageId, InsuranceCoverageRequest coverageRequest) {
        Ancillary ancillary = ancillaryRepository.findById(coverageRequest.getAncillaryId())
                .orElseThrow(() -> new ResourceNotFoundException("Ancillary not found"));

        InsuranceCoverage insuranceCoverage = insuranceCoverageRepository.findById(coverageId)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance coverage not found"));

        InsuranceCoverageMapper.updateEntity(insuranceCoverage, coverageRequest, ancillary);
        insuranceCoverageRepository.save(insuranceCoverage);
        return InsuranceCoverageMapper.toResponse(insuranceCoverage);
    }

    @Override
    public void deleteCoverage(Long coverageId) {
        InsuranceCoverage insuranceCoverage = insuranceCoverageRepository.findById(coverageId)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance coverage not found"));
        insuranceCoverageRepository.delete(insuranceCoverage);
    }

    @Override
    public InsuranceCoverageResponse getCoverageById(Long coverageId) {
        InsuranceCoverage insuranceCoverage = insuranceCoverageRepository.findById(coverageId)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance coverage not found"));
        return InsuranceCoverageMapper.toResponse(insuranceCoverage);
    }

    @Override
    public List<InsuranceCoverageResponse> getCoverageByAncillaryId(Long ancillaryId) {
        return insuranceCoverageRepository.findByAncillaryId(ancillaryId).stream()
                .map(InsuranceCoverageMapper::toResponse)
                .toList();
    }

    @Override
    public List<InsuranceCoverageResponse> getActiveCoverageByAncillaryId(Long ancillaryId) {
        return insuranceCoverageRepository.findByAncillaryIdAndActiveTrue(ancillaryId, true).stream()
                .map(InsuranceCoverageMapper::toResponse)
                .toList();
    }

    @Override
    public List<InsuranceCoverageResponse> getAllCoverages() {
        return insuranceCoverageRepository.findAll().stream()
                .map(InsuranceCoverageMapper::toResponse)
                .toList();
    }
}
