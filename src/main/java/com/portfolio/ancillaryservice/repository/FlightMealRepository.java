package com.portfolio.ancillaryservice.repository;

import com.portfolio.ancillaryservice.model.FlightMeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightMealRepository extends JpaRepository<FlightMeal, Long> {
    boolean existsByFlightIdAndMeal_Id(Long flightId, Long mealId);

    boolean existsByFlightIdAndMealId(Long flightId, Long mealId);

    List<FlightMeal> findByFlightId(Long flightId);
}
