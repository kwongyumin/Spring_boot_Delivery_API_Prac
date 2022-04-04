package com.example.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders{
    //Order는 아이템보다 더 상위 객체라고 생각하자.
    //아이디값이 있을거고
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //레스토랑마다 주문이 있을거니깐 ,
    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    //해당모든 주문들이 들어와야한다.
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private List<OrderItem> OrderItems;


    //배달료가 있을거고
    @Column(nullable = false)
    private int deliveryFee;
    //총가격이 있을거고 ( + 배달료)
    @Column(nullable = false)
    private int totalPrice;





}
