package com.example.delivery.domain;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Food {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //음식 pk

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private int foodPrice;


    @ManyToOne
    @JoinColumn(name="restaurant_Id",nullable = false)
    private Restaurant restaurant;




}
