//package com.example.delivery.domain;
//
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Builder
//@Entity
//public class OrderItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//
//    @Column(nullable = false)
//    private int quantity;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private int price;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    private Food food;
//
//    @ManyToOne
//    private Orders orders;
//
//}
