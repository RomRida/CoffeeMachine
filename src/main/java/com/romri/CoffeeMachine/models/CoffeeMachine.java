package com.romri.CoffeeMachine.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coffee_machine")
@Getter
@Setter
public class CoffeeMachine {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "status")
    private String status;
    @Column(name = "amountOfWater")
    private String amountOfWater;
    @Column(name = "amountOfCoffeeBeans")
    private String amountOfCoffeeBeans;
}
