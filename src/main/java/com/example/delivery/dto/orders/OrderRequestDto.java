package com.example.delivery.dto.orders;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequestDto {

    private Long restaurantId;
    private List<FoodOrderRequestDto> foods;






}
