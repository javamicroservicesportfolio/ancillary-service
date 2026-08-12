package com.portfolio.ancillaryservice.mapper;

import com.portfolio.ancillaryservice.model.FlightMeal;
import com.portfolio.ancillaryservice.model.Meal;
import com.portfolio.payload.request.FlightMealRequest;
import com.portfolio.payload.response.FlightMealResponse;

public class FlightMealMapper {
    public static FlightMeal toEntity(FlightMealRequest request, Meal meal) {

        if (request == null) {
            return null;
        }

        return FlightMeal.builder()
                .flightId(request.getFlightId())
                .meal(meal)
                .price(request.getPrice())
                .available(request.getAvailable())
                .displayOrder(request.getDisplayOrder())
                .build();
    }

    public static FlightMealResponse toResponse(FlightMeal flightMeal) {
        return FlightMealResponse.builder()
                .id(flightMeal.getId())
                .flightId(flightMeal.getFlightId())
                .meal(MealMapper.toResponse(flightMeal.getMeal()))
                .displayOrder(flightMeal.getDisplayOrder())
                .price(flightMeal.getPrice())
                .available(flightMeal.getAvailable())
                .build();
    }
}
