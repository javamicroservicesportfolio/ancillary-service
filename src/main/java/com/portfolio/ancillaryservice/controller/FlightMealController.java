package com.portfolio.ancillaryservice.controller;

import com.portfolio.ancillaryservice.service.FlightMealService;
import com.portfolio.payload.request.FlightMealRequest;
import com.portfolio.payload.response.FlightMealResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/flight-meals")
@RestController
@RequiredArgsConstructor
public class FlightMealController {
    private final FlightMealService flightMealService;

    @PostMapping
    public ResponseEntity<FlightMealResponse> createFlightMeal(@Valid @RequestBody FlightMealRequest request) {
        FlightMealResponse response = flightMealService.createFlightMeal(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightMealResponse> getFlightMealById(@PathVariable Long id) {
        FlightMealResponse response = flightMealService.getFlightMealById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<FlightMealResponse>> getByFlightId(@PathVariable Long flightId) {
        List<FlightMealResponse> response = flightMealService.getByFlightId(flightId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FlightMealResponse>> getAllByIdList(@RequestParam List<Long> ids) {
        List<FlightMealResponse> response = flightMealService.getAllByIdList(ids);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightMealResponse> updateFlightMeal(@PathVariable Long id, @Valid @RequestBody FlightMealRequest request) {
        FlightMealResponse response = flightMealService.updateFlightMeal(id, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<FlightMealResponse> updateFlightMealAvailability(@PathVariable Long id, @RequestParam Boolean available) {
        FlightMealResponse response = flightMealService.updateFlightMealAvailability(id, available);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlightMeal(@PathVariable Long id) {
        flightMealService.deleteFlightMeal(id);
        return ResponseEntity.noContent().build();
    }
}
