package com.example.delivery.service;




import com.example.delivery.domain.Restaurant;
import com.example.delivery.dto.RestaurantDto;
import com.example.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository rp; //restaurantRepository

    //음식점 등록 전 확인
    @Transactional
    public Restaurant confirmRegister(RestaurantDto restaurantDto) {
        //유효성검사
        int minOrderPrice = restaurantDto.getMinOrderPrice();
        int deliveryFee = restaurantDto.getDeliveryFee();

        checkMinOrderPrice(minOrderPrice);
        checkDeliveryFee(deliveryFee);



        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDto.getName())
                .minOrderPrice(minOrderPrice)
                .deliveryFee(deliveryFee)
                .build();


        rp.save(restaurant);

        return restaurant;
    }


    //음식점 등록 조건 1, minOrderPrice 조건검사 (1,000 ~ 10,000) 단위는 100만 허용.

    private void checkMinOrderPrice(int minOrderPrice){

        if(!(minOrderPrice >= 1000 && minOrderPrice <= 100000)){
            throw new IllegalStateException("금액 설정 범위 초과");

        }
        if(minOrderPrice % 100 > 0){
            throw new IllegalStateException("금액 단위 오류");
        }

    }

    // 기본 배달비 조건 2, 허용 값 0 ~ 10000 / 500단위로만 가능,

    private void checkDeliveryFee(int deliveryFee){

        if(deliveryFee < 0 || deliveryFee > 10000){
            throw new IllegalStateException("금액 설정 범위 초과");

        }

        if(deliveryFee % 500 > 0){
            throw new IllegalStateException("금액 단위 오류");
        }


    }
    //등록된 모든 음식점 조회
    @Transactional
    public List<Restaurant> findAllRestaurant() {

        return rp.findAll();
    }
}
