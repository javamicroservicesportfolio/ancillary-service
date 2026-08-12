package com.portfolio.ancillaryservice.service;

import com.portfolio.payload.request.FlightMealRequest;
import com.portfolio.payload.response.FlightMealResponse;

import java.util.List;

public interface FlightMealService {
    FlightMealResponse createFlightMeal(FlightMealRequest flightMealRequest);
    FlightMealResponse getFlightMealById(Long flightMealId);
    List<FlightMealResponse> getByFlightId(Long flightId);
    List<FlightMealResponse> getAllByIdList(List<Long> flightMealIds);
    FlightMealResponse updateFlightMeal(Long flightMealId, FlightMealRequest flightMealRequest);
    void deleteFlightMeal(Long flightMealId);
    FlightMealResponse updateFlightMealAvailability(Long flightMealId, Boolean available);
    Double calculateMealPrice(List<Long> mealIds);
}
