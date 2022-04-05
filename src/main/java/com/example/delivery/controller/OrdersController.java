package com.example.delivery.controller;


import com.example.delivery.dto.orders.OrderDto;
import com.example.delivery.dto.orders.OrderRequestDto;
import com.example.delivery.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class OrdersController {

    private final OrdersService ordersService;


    @PostMapping("/order/request")
    public OrderDto orderRequest(@RequestBody OrderRequestDto OrderRequestDto){

        //음식점 번호 받아오고 , 음식 번호 / 수량 받아온다.
        return ordersService.orderResponse(OrderRequestDto);


    }

    @GetMapping("/orders")
    public List<OrderDto> checkOrders(){

        return ordersService.checkAllOrders();

    }




}

/*

{
    response
  restaurantName: "쉐이크쉑 청담점",
  foods: [
    {
      name: "쉑버거 더블",
      quantity: 1,
      price: 10900
    },
    {
      name: "치즈 감자튀김",
      quantity: 2,
      price: 9800
    },
    {
      name: "쉐이크",
      quantity: 3,
      price: 17700
    }
  ],
  deliveryFee: 2000,
  totalPrice: 40400
}


 */