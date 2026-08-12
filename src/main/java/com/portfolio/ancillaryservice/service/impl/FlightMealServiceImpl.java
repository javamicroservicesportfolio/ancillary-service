package com.portfolio.ancillaryservice.service.impl;

import com.portfolio.ancillaryservice.mapper.FlightMealMapper;
import com.portfolio.ancillaryservice.model.FlightMeal;
import com.portfolio.ancillaryservice.model.Meal;
import com.portfolio.ancillaryservice.repository.FlightMealRepository;
import com.portfolio.ancillaryservice.repository.MealRepository;
import com.portfolio.ancillaryservice.service.FlightMealService;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.payload.request.FlightMealRequest;
import com.portfolio.payload.response.FlightMealResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FlightMealServiceImpl implements FlightMealService {
    private final MealRepository mealRepository;
    private final FlightMealRepository flightMealRepository;

    @Override
    public FlightMealResponse createFlightMeal(FlightMealRequest flightMealRequest) {
        Meal meal = mealRepository.findById(flightMealRequest.getMealId())
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id: " + flightMealRequest.getMealId()));


        if (flightMealRepository.existsByFlightIdAndMealId(flightMealRequest.getFlightId(), flightMealRequest.getMealId())) {
            throw new IllegalArgumentException("Flight meal already exists for flightId: " + flightMealRequest.getFlightId() + " and mealId: " + flightMealRequest.getMealId());
        }

        FlightMeal flightMeal = FlightMealMapper.toEntity(flightMealRequest, meal);

        flightMealRepository.save(flightMeal);
        return FlightMealMapper.toResponse(flightMeal);
    }

    @Override
    public FlightMealResponse getFlightMealById(Long flightMealId) {
        FlightMeal flightMeal = flightMealRepository.findById(flightMealId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight meal not found with id: " + flightMealId));
        return FlightMealMapper.toResponse(flightMeal);
    }

    @Override
    public List<FlightMealResponse> getByFlightId(Long flightId) {
        return flightMealRepository.findByFlightId(flightId)
                .stream()
                .map(FlightMealMapper::toResponse)
                .toList();
    }

    @Override
    public List<FlightMealResponse> getAllByIdList(List<Long> flightMealIds) {
        return flightMealRepository.findAllById(flightMealIds)
                .stream()
                .map(FlightMealMapper::toResponse)
                .toList();
    }

    @Override
    public FlightMealResponse updateFlightMeal(Long flightMealId, FlightMealRequest flightMealRequest) {
        FlightMeal flightMeal = flightMealRepository.findById(flightMealId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight meal not found with id: " + flightMealId));

        Meal meal = mealRepository.findById(flightMealRequest.getMealId())
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id: " + flightMealRequest.getMealId()));

        flightMeal.setFlightId(flightMealRequest.getFlightId());
        flightMeal.setMeal(meal);
        flightMeal.setPrice(flightMealRequest.getPrice());
        flightMeal.setAvailable(flightMealRequest.getAvailable());
        flightMeal.setDisplayOrder(flightMealRequest.getDisplayOrder());

        flightMealRepository.save(flightMeal);
        return FlightMealMapper.toResponse(flightMeal);
    }

    @Override
    public void deleteFlightMeal(Long flightMealId) {
        FlightMeal flightMeal = flightMealRepository.findById(flightMealId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight meal not found with id: " + flightMealId));
        flightMealRepository.delete(flightMeal);
    }

    @Override
    public FlightMealResponse updateFlightMealAvailability(Long flightMealId, Boolean available) {
        FlightMeal flightMeal = flightMealRepository.findById(flightMealId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight meal not found with id: " + flightMealId));
        flightMeal.setAvailable(available);
        flightMealRepository.save(flightMeal);
        return FlightMealMapper.toResponse(flightMeal);
    }

    @Override
    public Double calculateMealPrice(List<Long> mealIds) {
        List<FlightMeal> meals = flightMealRepository.findAllById(mealIds);
        double price = 0.0;
        for (FlightMeal meal : meals) {
            price += Optional.ofNullable(meal.getPrice()).orElse(0.0);
        }
        return price;
    }
}
