package com.example.delivery.dto.orders;



import com.example.delivery.domain.Orders;
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


//
//    public OrderDto(Orders orders, int deliveryFee, List<FoodOrderDto> foods){
//
//        this.restaurantName = orders.getRestaurantName();
//        this.foods = foods;
//        this.deliveryFee = deliveryFee;
//        this.totalPrice = orders.getTotalPrice();
//
//    }

}
