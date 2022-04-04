package com.example.delivery.controller;


import com.example.delivery.domain.Food;
import com.example.delivery.dto.FoodDto;
import com.example.delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {


    private final FoodService fs; //foodService
    //음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void register(@PathVariable Long restaurantId,  @RequestBody List<FoodDto> foodDtoList){

        //음식을 한번에 여러가지를 등록할 수 있도록 lIST 타입으로 받아준다.
        fs.confirmResister(restaurantId,foodDtoList);
    }

    //메뉴판 조회

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> findAllFoodByRestaurantId(@PathVariable Long restaurantId){

        return fs.findAllRestaurantFood(restaurantId);
    }

}
