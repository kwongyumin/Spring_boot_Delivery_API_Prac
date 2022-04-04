package com.example.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem {
    //Orders 안에 들어가야함.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    //수량 받고
    @Column(nullable = false)
    private int quantity;
    //가격 받고
    @Column(nullable = false)
    private int price;

    //음식 주문 시 음식정보를 빼내온다.
    @ManyToOne(cascade = CascadeType.ALL)
    private Food food;

    // 이 정보들이 최종 오더에 들어가야함.
    @ManyToOne
    private Orders orders;



}