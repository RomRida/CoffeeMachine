package com.romri.coffeemachine.controllers;

import com.romri.coffeemachine.models.CoffeeMachine;
import com.romri.coffeemachine.models.CoffeeType;
import com.romri.coffeemachine.services.CoffeeMachineService;
import com.romri.coffeemachine.services.CoffeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/coffee-machine")
public class CoffeeMachineController {
    private final CoffeeMachineService coffeeMachineService;
    private final CoffeeTypeService coffeeTypeService;

    @Autowired
    public CoffeeMachineController(CoffeeMachineService coffeeMachineService, CoffeeTypeService coffeeTypeService) {
        this.coffeeMachineService = coffeeMachineService;
        this.coffeeTypeService = coffeeTypeService;
    }

    @GetMapping("/status")
    public CoffeeMachine getMachineStatus(@RequestParam(name = "id") Long id) {
        return coffeeMachineService.getCoffeeMachineStatusById(id);
    }

    @PutMapping("/add-beans")
    public CoffeeMachine addBeans(@RequestParam(name = "id") Long id, @RequestParam(name = "beans") Double kg) {
        if (kg <= 0){
            throw new IllegalArgumentException("You can't put negative amount of coffee beans");
        }else if (kg > 5){
            throw new IllegalArgumentException("You can't put so much beans, try putting less then 5kg at time");
        }else {
            return coffeeMachineService.addBeans(id, kg);
        }
    }

    @PutMapping("/add-water")
    public CoffeeMachine addWater(@RequestParam(name = "id") Long id, @RequestParam(name = "water") Double l) {
        if (l <= 0){
            throw new IllegalArgumentException("You can't put negative amount of water");
        }else if (l > 5){
            throw new IllegalArgumentException("You can't put so much water, try putting less then 5l at time");
        }else {
            return coffeeMachineService.addWater(id, l);
        }
    }

    @PutMapping("/add-milk")
    public CoffeeMachine addMilk (@RequestParam(name = "id") Long id, @RequestParam(name = "milk") Double l) {
        if (l <= 0){
            throw new IllegalArgumentException("You can't put negative amount of milk");
        }else if (l > 5){
            throw new IllegalArgumentException("You can't put so much milk, try putting less then 5l at time");
        }else {
            return coffeeMachineService.addMilk(id, l);
        }
    }

    @PutMapping("/turn-on-or-off")
    public CoffeeMachine turnOnOrOff(@RequestParam(name = "id") Long id) {
        return coffeeMachineService.turnOnOrOff(id);
    }

    @GetMapping("/make-coffee")
    public String makeCoffee(@RequestParam(name = "id") Long id, @RequestParam(name = "type") String type) {
        return coffeeMachineService.makeCoffee(id, type);
    }

    @GetMapping("/see-all-coffee-types")
    public List<CoffeeType> getAllCoffeeTypes(){
        return coffeeTypeService.findAll();
    }

}
