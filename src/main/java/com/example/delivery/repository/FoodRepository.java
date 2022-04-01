package com.example.delivery.repository;

import com.example.delivery.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food,Long> {

        //특정 (pk) 에 해당하는 음식점의 음식을 찾아서 반환해준다.
        Optional<Food> findByRestaurantIdAndFoodName(Long RestaurantId,String foodName);
        List<Food> findAllByRestaurantId(Long Id);
}
