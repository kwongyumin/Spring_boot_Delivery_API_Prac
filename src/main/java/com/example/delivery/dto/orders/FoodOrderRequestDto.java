package com.example.delivery.dto.orders;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FoodOrderRequestDto {
    Long id;
    int quantity;
}
