package com.portfolio.ancillaryservice.controller;

import com.portfolio.ancillaryservice.service.AncillaryService;
import com.portfolio.payload.request.AncillaryRequest;
import com.portfolio.payload.response.AncillaryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ancillaries")
public class AncillaryController {

    private final AncillaryService ancillaryService;

    @PostMapping
    public ResponseEntity<AncillaryResponse> createAncillary(@Valid @RequestBody AncillaryRequest request, @RequestHeader("X-Airline-Id") Long airlineId) {
        AncillaryResponse response = ancillaryService.createAncillary(airlineId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AncillaryResponse> getAncillaryById(@PathVariable Long id) {
        AncillaryResponse response = ancillaryService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AncillaryResponse>> getAncillariesByAirlineId(@RequestHeader("X-Airline-Id") Long airlineId) {
        return ResponseEntity.ok(ancillaryService.getByAirlineId(airlineId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AncillaryResponse> updateAncillary(@PathVariable Long id, @Valid @RequestBody AncillaryRequest request) {
        AncillaryResponse response = ancillaryService.updateAncillary(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAncillary(@PathVariable Long id) {
        ancillaryService.deleteAncillary(id);
        return ResponseEntity.noContent().build();
    }
}
