package com.example.delivery.service;


import com.example.delivery.domain.Food;
import com.example.delivery.domain.OrderItem;
import com.example.delivery.domain.Restaurant;
import com.example.delivery.dto.orders.FoodOrderRequestDto;
import com.example.delivery.dto.orders.OrderDto;
import com.example.delivery.dto.orders.OrderRequestDto;
import com.example.delivery.repository.FoodRepository;
import com.example.delivery.repository.OrderItemRepository;
import com.example.delivery.repository.OrderRepository;
import com.example.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

            /*
        {
              restaurantId: 1
              foods: [
                { id: 1, quantity: 1 },
                { id: 2, quantity: 2 },
                { id: 3, quantity: 3 }
              ]
            }
         */



    public OrderDto orderResponse(OrderRequestDto OrderRequestDto) {


        Optional<Restaurant> findRestaurant = restaurantRepository.findById(OrderRequestDto.getRestaurantId());

        //식당 유무 확인 ,
        Restaurant restaurant = findRestaurant.orElseThrow(
                () -> new IllegalArgumentException("찾으시는 식당이 없습니다.")
        );
        //레스토랑의 ID값을 기준으로 이름을 가져온다.
        String restaurantName = restaurant.getName();


        //FoodOrderRequestDto에서 food 리스트를 꺼내온다(음식의 id값과 수량이 있음,).
        List<FoodOrderRequestDto> foodOrderItems = OrderRequestDto.getFoods(); // foods
        List<OrderItem> OrdersItem = new ArrayList<>();

        for(FoodOrderRequestDto foodOrderRequestDto :foodOrderItems){
            //음식 1번의  수량만 꺼내서 유효성 검사 ,
            int quantity = foodOrderRequestDto.getQuantity();
            checkQuantity(quantity);
            //음식1번의 아이디값을 가져와서 정보를 가져온다. // 가격과 레스토랑 정보가 추가적으로 담겨져있다.
            Food food = foodRepository.getById(foodOrderRequestDto.getId());
            //음식 1번의 음식명을 꺼내보자.

            //주문 아이템에 빌드업 ,
            OrderItem orderItem = OrderItem.builder()
                    .quantity(quantity)
                    .price(food.getPrice()*quantity)
                    .food(food)
                    .name(food.getName())
                    .build();
            //음식 1, 2 , 3, 저장..
            orderItemRepository.save(orderItem);



        }

    }
    //수량 오류 체크 ,
    private void checkQuantity(int quantity){
        if(!(quantity > 1 && quantity < 100)) throw new IllegalArgumentException("음식 수량 범위 오류");


    }

}
