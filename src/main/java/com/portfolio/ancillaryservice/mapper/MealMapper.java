package com.portfolio.ancillaryservice.mapper;

import com.portfolio.ancillaryservice.model.Meal;
import com.portfolio.payload.request.MealRequest;
import com.portfolio.payload.response.MealResponse;

public class MealMapper {
    public static Meal toEntity(MealRequest request, Long airlineId) {
        if (request == null) {
            return null;
        }


        return Meal.builder()
                .code(request.getCode())
                .name(request.getName())
                .mealType(request.getMealType())
                .dietaryRestriction(request.getDietaryRestriction())
                .ingredients(request.getIngredients())
                .imageUrl(request.getImageUrl())
                .requiresAdvanceBooking(request.getRequiresAdvanceBooking())
                .advanceBookingHours(request.getAdvanceBookingHours())
                .displayOrder(request.getDisplayOrder())
                .airlineId(airlineId)
                .build();
    }

    public static MealResponse toResponse(Meal meal) {
        if (meal == null) {
            return null;
        }

        return MealResponse.builder()
                .id(meal.getId())
                .code(meal.getCode())
                .name(meal.getName())
                .mealType(meal.getMealType())
                .dietaryRestriction(meal.getDietaryRestriction())
                .ingredients(meal.getIngredients())
                .imageUrl(meal.getImageUrl())
                .available(meal.getAvailable())
                .requiresAdvanceBooking(meal.getRequiresAdvanceBooking())
                .advanceBookingHours(meal.getAdvanceBookingHours())
                .displayOrder(meal.getDisplayOrder())
                .airlineId(meal.getAirlineId())
                .createdAt(meal.getCreatedAt())
                .updatedAt(meal.getUpdatedAt())
                .build();
    }
}
