package com.example.delivery.controller;


import com.example.delivery.domain.Restaurant;
import com.example.delivery.dto.RestaurantDto;
import com.example.delivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService rs; //restaurantService


    //음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant register(@RequestBody RestaurantDto restaurantDto){

        return rs.confirmRegister(restaurantDto);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> findAllRestaurant(){

        return rs.findAllRestaurant();
    }

}
