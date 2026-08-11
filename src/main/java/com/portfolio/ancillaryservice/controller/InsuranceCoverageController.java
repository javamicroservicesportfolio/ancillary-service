package com.portfolio.ancillaryservice.controller;

import com.portfolio.ancillaryservice.service.InsuranceCoverageService;
import com.portfolio.payload.request.InsuranceCoverageRequest;
import com.portfolio.payload.response.InsuranceCoverageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/insurance-coverages")
public class InsuranceCoverageController {
    private final InsuranceCoverageService insuranceCoverageService;

    @PostMapping
    public ResponseEntity<InsuranceCoverageResponse> createInsuranceCoverage(@Valid @RequestBody InsuranceCoverageRequest coverageRequest) {
        InsuranceCoverageResponse response = insuranceCoverageService.createCoverage(coverageRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsuranceCoverageResponse> updateInsuranceCoverage(@PathVariable Long id, @Valid @RequestBody InsuranceCoverageRequest coverageRequest) {
        InsuranceCoverageResponse response = insuranceCoverageService.updateCoverage(id, coverageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsuranceCoverage(@PathVariable Long id) {
        insuranceCoverageService.deleteCoverage(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceCoverageResponse> getInsuranceCoverageById(@PathVariable Long id) {
        InsuranceCoverageResponse response = insuranceCoverageService.getCoverageById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/ancillary/{ancillaryId}")
    public ResponseEntity<List<InsuranceCoverageResponse>> getInsuranceCoveragesByAncillaryId(@PathVariable Long ancillaryId) {
        List<InsuranceCoverageResponse> responses = insuranceCoverageService.getCoverageByAncillaryId(ancillaryId);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/ancillary/{ancillaryId}/active")
    public ResponseEntity<List<InsuranceCoverageResponse>> getActiveInsuranceCoveragesByAncillaryId(@PathVariable Long ancillaryId) {
        List<InsuranceCoverageResponse> responses = insuranceCoverageService.getActiveCoverageByAncillaryId(ancillaryId);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
}
