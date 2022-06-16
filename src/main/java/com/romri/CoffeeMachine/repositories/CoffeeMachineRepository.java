package com.romri.CoffeeMachine.repositories;

import com.romri.CoffeeMachine.models.CoffeeMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeMachineRepository extends JpaRepository<CoffeeMachine, Long> {
}
