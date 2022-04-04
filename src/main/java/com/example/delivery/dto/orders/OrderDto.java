package com.example.delivery.dto.orders;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDto {
    private String restaurantName;
    private List<FoodOrderDto> foods;
    private int deliveryFee;
    private int totalPrice;



}
