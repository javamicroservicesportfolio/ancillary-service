package com.portfolio.ancillaryservice.service.impl;

import com.portfolio.ancillaryservice.mapper.MealMapper;
import com.portfolio.ancillaryservice.model.Meal;
import com.portfolio.ancillaryservice.repository.MealRepository;
import com.portfolio.ancillaryservice.service.MealService;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.payload.request.MealRequest;
import com.portfolio.payload.response.MealResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;

    @Override
    public MealResponse createMeal(MealRequest mealRequest, Long airlineId) {
        if (mealRepository.existsByCodeAndAirlineId(mealRequest.getCode(), airlineId)) {
            throw new ResourceNotFoundException("Meal with code " + mealRequest.getCode() + " already exists for this airline.");
        }

        Meal meal = MealMapper.toEntity(mealRequest, airlineId);
        mealRepository.save(meal);

        return MealMapper.toResponse(meal);
    }

    @Override
    public MealResponse getMealById(Long id) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id: " + id));
        return MealMapper.toResponse(meal);
    }

    @Override
    public List<MealResponse> getMealsByAirlineId(Long airlineId) {
        List<Meal> meals = mealRepository.findByAirlineId(airlineId);
        return meals.stream()
                .map(MealMapper::toResponse)
                .toList();
    }

    @Override
    public MealResponse updateMeal(Long id, Long airlineId ,MealRequest mealRequest) {
        Meal existingMeal = mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id: " + id));

        if (mealRepository.existsByAirlineIdAndCodeAndIdNot(existingMeal.getAirlineId(), mealRequest.getCode(), id)) {
            throw new ResourceNotFoundException("Meal with code " + mealRequest.getCode() + " already exists for this airline.");
        }

        existingMeal.setCode(mealRequest.getCode());
        existingMeal.setName(mealRequest.getName());
        existingMeal.setMealType(mealRequest.getMealType());
        existingMeal.setDietaryRestriction(mealRequest.getDietaryRestriction());
        existingMeal.setIngredients(mealRequest.getIngredients());
        existingMeal.setImageUrl(mealRequest.getImageUrl());
        existingMeal.setRequiresAdvanceBooking(mealRequest.getRequiresAdvanceBooking());
        existingMeal.setAdvanceBookingHours(mealRequest.getAdvanceBookingHours());
        existingMeal.setDisplayOrder(mealRequest.getDisplayOrder());

        mealRepository.save(existingMeal);

        return MealMapper.toResponse(existingMeal);
    }

    @Override
    public void deleteMeal(Long id) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id: " + id));
        mealRepository.delete(meal);
    }

    @Override
    public MealResponse updateMealAvailability(Long id, Boolean available) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id: " + id));
        meal.setAvailable(available);
        mealRepository.save(meal);
        return MealMapper.toResponse(meal);
    }
}
