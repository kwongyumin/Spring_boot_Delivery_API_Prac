package com.example.delivery.dto.orders;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FoodOrderDto {
    String name;
    int quantity;
    int price;
}
