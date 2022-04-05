package com.example.delivery.service;


import com.example.delivery.domain.Food;
import com.example.delivery.domain.OrderItem;
import com.example.delivery.domain.Orders;
import com.example.delivery.domain.Restaurant;
import com.example.delivery.dto.orders.FoodOrderDto;
import com.example.delivery.dto.orders.FoodOrderRequestDto;
import com.example.delivery.dto.orders.OrderDto;
import com.example.delivery.dto.orders.OrderRequestDto;
import com.example.delivery.repository.FoodRepository;
import com.example.delivery.repository.OrderItemRepository;
import com.example.delivery.repository.OrdersRepository;
import com.example.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
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


    @Transactional
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
        List<FoodOrderDto> foodOrdersDto = new ArrayList<>();
        List<OrderItem> orderItemList = new ArrayList<>();
        //음식들의 총가격
        int totalPrice = 0;


        for (FoodOrderRequestDto foodOrderRequestDto : foodOrderItems) {
            //음식 1번의  수량만 꺼내서 유효성 검사 ,
            int quantity = foodOrderRequestDto.getQuantity();
            checkQuantity(quantity);
            //음식1번의 아이디값을 가져와서 정보를 가져온다. // 가격과 레스토랑 정보가 추가적으로 담겨져있다.
            Food food = foodRepository.getById(foodOrderRequestDto.getId());
            //음식 1번의 음식명을 꺼내보자.

            //음식 1번의 정보들을 음식 주문 DTO에 담는다.
            FoodOrderDto foodOrderDto = FoodOrderDto.builder()
                    .name(food.getName())
                    .price(food.getPrice() * quantity)
                    .quantity(quantity)
                    .build();

            //주문 아이템에 빌드업 ,
            OrderItem orderItem = OrderItem.builder()
                    .quantity(quantity) //음식 1번의 주문 수량
                    .price(food.getPrice() * quantity) // 음식1번의 가격
                    .food(food) //음식 1번에 대한 정보
                    .name(food.getName()) // 음식 1번의 이름
                    .build();

            //음식 1, 2 , 3, 저장..
            orderItemRepository.save(orderItem);
            //orders의 list의 전달하기 위해 itemList에 주문음식 저장 ,
            orderItemList.add(orderItem);

            //음식 1,2,3 을 OrderItem 타입의 리스트로 받아온다.
            foodOrdersDto.add(foodOrderDto);
            //주문 음식 가격들의 총 합을 누적
            totalPrice += (food.getPrice() * quantity);
            checkTotalPrice(restaurant, totalPrice);


        }
        //음식점 1의 배달료를 받아온다.
        int DeliveryFee = restaurant.getDeliveryFee();
        totalPrice += DeliveryFee;

        Orders orders = Orders.builder()
                .restaurant(restaurant)
                .restaurantName(restaurantName)
                .deliveryFee(DeliveryFee)
                .OrderItems(orderItemList)
                .totalPrice(totalPrice)
                .build();
        //주문 저장
        ordersRepository.save(orders);


        return OrderDto.builder()
                .foods(foodOrdersDto)
                .restaurantName(restaurantName)
                .deliveryFee(DeliveryFee)
                .totalPrice(totalPrice)
                .build();

    }
    //주문 요청한 모든 음식들 조회
    public List<OrderDto> checkAllOrders() {
        //OrderDto 음식점 이름, 음식(이름,수량,가격) , 배달비 , 총가격
        //찾아서 반환해주자.
        List<OrderDto> orderDtoList = new ArrayList<>();

        //요청된 Orders 의 전체목록을 불러오자.
        List<Orders> ordersList = ordersRepository.findAll();
        System.out.println("주문 전체목록 : "+ ordersList.size());
        //Orders에는 음식점 정보, 음식점 이름,  주문목록 정보, 배달료, 총가격이있다.

        //전체목록을 반복을 통해 하나하나 분리해보자.
        for(Orders orders : ordersList){
            //OrderDto에 들어갈 List를 만들어준다.
            List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();
            //name quantity price



            //주문서의 주문목록들을 가져온다.
            //주문목록에는 음식이름, 가격 ,수량 ,가격, 음식정보, 주문정보
            List<OrderItem> orderItemList = orders.getOrderItems();
            //OrderItems가 없음.

            for(OrderItem orderItems: orderItemList){
                System.out.println(orderItems);
                FoodOrderDto foodOrderDto = FoodOrderDto.builder()
                        .name(orderItems.getName())
                        .quantity(orderItems.getQuantity())
                        .price(orderItems.getPrice())
                        .build();

                foodOrderDtoList.add(foodOrderDto);
            }

            OrderDto orderDto = OrderDto.builder()
                    .restaurantName(orders.getRestaurantName())
                    .totalPrice(orders.getTotalPrice())
                    .deliveryFee(orders.getDeliveryFee())
                    .foods(foodOrderDtoList)
                    .build();

            orderDtoList.add(orderDto);


        }
        return orderDtoList;
    }

    /*
         {
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



    //수량 오류 체크 ,
    private void checkQuantity(int quantity){
        if(quantity < 1 || quantity > 100) throw new IllegalArgumentException("음식 수량 범위 오류");


    }
    //총가격과 최소 금액 계산 비교
    private void checkTotalPrice(Restaurant restaurant,int totalPrice){
        if(restaurant.getMinOrderPrice() > totalPrice) throw new IllegalArgumentException("최소 가격 오류");
    }


}
