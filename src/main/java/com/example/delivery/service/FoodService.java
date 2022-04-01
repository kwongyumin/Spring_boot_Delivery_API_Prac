package com.example.delivery.service;


import com.example.delivery.domain.Food;
import com.example.delivery.domain.Restaurant;
import com.example.delivery.dto.FoodDto;
import com.example.delivery.repository.FoodRepository;
import com.example.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository fr; // foodRepository
    private final RestaurantRepository rp; // restaurantRepository

    //특정 음식점에서 음식을 등록 . (특정 음식점 pk , 음식 리스트들을 받아온다.)
    @Transactional
    public void confirmResister(Long id, List<FoodDto> foodDtoList) {

        //음식점을 찾아온다.
        Optional<Restaurant> findByRestaurantId = rp.findById(id);
        //Optional을 .get()-> 꺼낸다.
        Restaurant restaurant = findByRestaurantId.get();



    //반복을 통해 리스트에서 음식들을 꺼내준다.
        for(FoodDto foodDto : foodDtoList){
            //특정 음식점에서 같은 이름의 음식을 등록할 수 없도록 해야한다.
           checkDuplicateFood(id,foodDto.getFoodName());

           checkFoodPrice(foodDto.getFoodPrice());

            Food food = Food.builder()
                    .foodName(foodDto.getFoodName())
                    .foodPrice(foodDto.getFoodPrice())
                    .restaurant(restaurant)
                    .build();

            //lIST의 값을 DTO 객체로 받아와 한개씩 저장 ,
            fr.save(food);

        }
    }

    // 특정 음식점의 Id 값에 해당하는 음식들을 모두 찾아온다.
    @Transactional
    public List<Food> findAllRestaurantFood(Long RestaurantId){
        return fr.findAllByRestaurantId(RestaurantId);

    }


    // 특정 음식점안에 중복된 이름의 음식을 예외 처리 ,
    private void checkDuplicateFood(Long RestaurantId,String foodName){

        Optional<Food> findFood = fr.findByRestaurantIdAndFoodName(RestaurantId,foodName);

        if(findFood.isPresent()){
            throw new IllegalStateException("해당 음식점에 이미 존재하는 음식 이름입니다.");
        }

    }
    // 금액 범위 조정
    private void checkFoodPrice(int foodPrice) {

        if(!(foodPrice > 100 && foodPrice < 1_000_000)){
            throw new IllegalStateException("금액 설정 범위 초과");
        }


        if(foodPrice % 100 > 0){
            throw new IllegalStateException("100원 단위로 입력해주세요");
        }

    }





}
