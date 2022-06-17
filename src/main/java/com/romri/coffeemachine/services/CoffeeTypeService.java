package com.romri.coffeemachine.services;

import com.romri.coffeemachine.models.CoffeeType;
import com.romri.coffeemachine.repositories.CoffeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeTypeService {
    private final CoffeeTypeRepository coffeeTypeRepository;

    @Autowired
    public CoffeeTypeService(CoffeeTypeRepository coffeeTypeRepository) {
        this.coffeeTypeRepository = coffeeTypeRepository;
    }

    public CoffeeType findByName(String typeName) {
        return coffeeTypeRepository.findByName(typeName).orElseThrow();
    }
}
