package com.example.delivery.domain;


import lombok.*;


import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Restaurant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //음식점 pk

    @Column(nullable = false)
    private String name;
    //음식적 이름

    @Column(nullable = false)
    private int minOrderPrice;
    //최소 주문 가격

    @Column(nullable = false)
    private int deliveryFee;
    //기본 배달비비



}
