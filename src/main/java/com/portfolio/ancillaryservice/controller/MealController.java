package com.portfolio.ancillaryservice.controller;

import com.portfolio.ancillaryservice.service.MealService;
import com.portfolio.payload.request.MealRequest;
import com.portfolio.payload.response.MealResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @PostMapping
    public ResponseEntity<MealResponse> createMeal(@RequestBody @Valid MealRequest mealRequest, @RequestHeader("X-Airline-Id") Long airlineId) {
        MealResponse mealResponse = mealService.createMeal(mealRequest, airlineId);
        return ResponseEntity.status(HttpStatus.CREATED).body(mealResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealResponse> getMealById(@PathVariable Long id) {
        MealResponse mealResponse = mealService.getMealById(id);
        return ResponseEntity.ok(mealResponse);
    }

    @GetMapping
    public ResponseEntity<List<MealResponse>> getMealsByAirlineId(@RequestHeader("X-Airline-Id") Long airlineId) {
        List<MealResponse> mealResponses = mealService.getMealsByAirlineId(airlineId);
        return ResponseEntity.ok(mealResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealResponse> updateMeal(@PathVariable Long id, @RequestHeader("X-Airline-Id") Long airlineId, @RequestBody @Valid MealRequest mealRequest) {
        MealResponse mealResponse = mealService.updateMeal(id, airlineId, mealRequest);
        return ResponseEntity.ok(mealResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }
}
