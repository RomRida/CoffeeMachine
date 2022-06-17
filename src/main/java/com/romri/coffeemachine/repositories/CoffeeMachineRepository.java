package com.romri.coffeemachine.repositories;

import com.romri.coffeemachine.models.CoffeeMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeMachineRepository extends JpaRepository<CoffeeMachine, Long> {
}
