//package com.example.delivery.domain;
//
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.List;
//
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Getter
//@Entity
//public class Orders {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    //주문번호
//
//    @Column(nullable = false)
//    private String restaurantName;
//
//
//    @Column(nullable = false)
//    private int totalPrice;
//    //총가격
//
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "orders_Id")
//    private List<OrderItem> foods;
//
//}
