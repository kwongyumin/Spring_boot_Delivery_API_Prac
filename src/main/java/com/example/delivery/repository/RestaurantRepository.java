package com.example.delivery.repository;

import com.example.delivery.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

        Optional<Restaurant> findById(Long id);




}
