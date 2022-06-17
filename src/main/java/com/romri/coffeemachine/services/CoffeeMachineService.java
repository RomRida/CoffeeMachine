package com.romri.coffeemachine.services;

import com.romri.coffeemachine.models.CoffeeMachine;
import com.romri.coffeemachine.models.CoffeeType;
import com.romri.coffeemachine.models.Power;
import com.romri.coffeemachine.repositories.CoffeeMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeMachineService {
    private final CoffeeMachineRepository coffeeMachineRepository;
    private final CoffeeTypeService coffeeTypeService;

    @Autowired
    public CoffeeMachineService(CoffeeMachineRepository coffeeMachineRepository, CoffeeTypeService coffeeTypeService) {
        this.coffeeMachineRepository = coffeeMachineRepository;
        this.coffeeTypeService = coffeeTypeService;
    }

    public CoffeeMachine getCoffeeMachineStatusById(Long id) {
        return coffeeMachineRepository.findById(id).orElseThrow();
    }

    public CoffeeMachine addBeans(Long id, Double kg) {
        CoffeeMachine coffeeMachine = getCoffeeMachineStatusById(id);
        Double amountOfBeansInMachine = coffeeMachine.getAmountOfCoffeeBeans();
        if (amountOfBeansInMachine + kg < 5) {
            coffeeMachine.setAmountOfCoffeeBeans(amountOfBeansInMachine + kg);
            return coffeeMachineRepository.save(coffeeMachine);
        } else {
            throw new IllegalArgumentException("Amount of beans is too much, try putting in less then 5kg total");
        }
    }

    public CoffeeMachine addWater(Long id, Double l) {
        CoffeeMachine coffeeMachine = getCoffeeMachineStatusById(id);
        Double amountOfWaterInMachine = coffeeMachine.getAmountOfWater();
        if (amountOfWaterInMachine + l < 5) {
            coffeeMachine.setAmountOfWater(amountOfWaterInMachine + l);
            return coffeeMachineRepository.save(coffeeMachine);
        } else {
            throw new IllegalArgumentException("Amount of water is too much, try putting in less then 5l total");
        }
    }

    public CoffeeMachine addMilk(Long id, Double l) {
        CoffeeMachine coffeeMachine = getCoffeeMachineStatusById(id);
        Double amountOfMilkInMachine = coffeeMachine.getAmountOfMilk();
        if (amountOfMilkInMachine + l < 3) {
            coffeeMachine.setAmountOfMilk(amountOfMilkInMachine + l);
            return coffeeMachineRepository.save(coffeeMachine);
        } else {
            throw new IllegalArgumentException("Amount of milk is too much, try putting in less then 3l total");
        }
    }

    public CoffeeMachine turnOnOrOff(Long id) {
        CoffeeMachine coffeeMachine = getCoffeeMachineStatusById(id);
        Power currentPower = coffeeMachine.getStatus();
        switch (currentPower) {
            case ON -> coffeeMachine.setStatus(Power.OFF);
            case OFF -> coffeeMachine.setStatus(Power.ON);
        }
        return coffeeMachineRepository.save(coffeeMachine);
    }

    public String makeCoffee(Long id, String typeName) {
        CoffeeMachine coffeeMachine = getCoffeeMachineStatusById(id);
        if (coffeeMachine.getStatus() == Power.OFF){
            throw new IllegalArgumentException("You need to turn machine on first");
        }
        CoffeeType type = coffeeTypeService.findByName(typeName);
        double neededAmountOfBeans = type.getNeededAmountOfCoffeeBeans();
        double neededAmountOfWater = type.getNeededAmountOfWater();
        double neededAmountOfMilk = type.getNeededAmountOfMilk();
        StringBuilder stringBuilder = new StringBuilder();
        if (neededAmountOfBeans > coffeeMachine.getAmountOfCoffeeBeans()) {
            stringBuilder.append("beans, ");
        }
        if (neededAmountOfWater > coffeeMachine.getAmountOfWater()) {
            stringBuilder.append("water, ");
        }
        if (neededAmountOfMilk > coffeeMachine.getAmountOfMilk()) {
            stringBuilder.append("milk, ");
        }
        if (stringBuilder.isEmpty()) {
            coffeeMachine.setCoffeeType(type);
            coffeeMachine.setAmountOfCoffeeBeans(coffeeMachine.getAmountOfCoffeeBeans() - neededAmountOfBeans);
            coffeeMachine.setAmountOfCoffeeBeans(coffeeMachine.getAmountOfCoffeeBeans() - neededAmountOfBeans);
            coffeeMachine.setAmountOfCoffeeBeans(coffeeMachine.getAmountOfCoffeeBeans() - neededAmountOfBeans);
            coffeeMachineRepository.save(coffeeMachine);
            return "Here you go, your " + type.getName();
        } else {
            throw new IllegalArgumentException("Not enough " + stringBuilder + "make sure there is enough for desired drink");
        }
    }

}
