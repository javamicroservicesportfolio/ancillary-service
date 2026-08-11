package com.portfolio.ancillaryservice.service;

import com.portfolio.payload.request.MealRequest;
import com.portfolio.payload.response.MealResponse;

import java.util.List;

public interface MealService {
    MealResponse createMeal(MealRequest mealRequest, Long airlineId);
    MealResponse getMealById(Long id);
    List<MealResponse> getMealsByAirlineId(Long airlineId);
    MealResponse updateMeal(Long id, Long airlineId, MealRequest mealRequest);
    void deleteMeal(Long id);
    MealResponse updateMealAvailability(Long id, Boolean available);
}
