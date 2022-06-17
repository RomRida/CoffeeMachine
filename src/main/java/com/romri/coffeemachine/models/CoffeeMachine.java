package com.romri.coffeemachine.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "coffee_machine")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeMachine {
    @Id
    @Column(name = "id")
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Power status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coffeeTypeId", referencedColumnName = "id")
    private CoffeeType coffeeType;
    @Column(name = "amountOfWater")
    private Double amountOfWater;
    @Column(name = "amountOfMilk")
    private Double amountOfMilk;
    @Column(name = "amountOfCoffeeBeans")
    private Double amountOfCoffeeBeans;
}
