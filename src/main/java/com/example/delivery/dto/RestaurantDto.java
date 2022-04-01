package com.example.delivery.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class RestaurantDto {

    private String name;
    private int minOrderPrice;
    private int deliveryFee;

}
