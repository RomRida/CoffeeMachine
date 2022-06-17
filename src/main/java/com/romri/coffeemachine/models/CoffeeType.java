package com.romri.coffeemachine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "coffee_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeType {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "neededAmountOfWater")
    private Double neededAmountOfWater;
    @Column(name = "neededAmountOfMilk")
    private Double neededAmountOfMilk;
    @Column(name = "neededAmountOfCoffeeBeans")
    private Double neededAmountOfCoffeeBeans;

    @OneToOne(mappedBy = "coffeeType")
    private CoffeeMachine coffeeMachine;
}
