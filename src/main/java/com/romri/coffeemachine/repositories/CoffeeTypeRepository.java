package com.romri.coffeemachine.repositories;

import com.romri.coffeemachine.models.CoffeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoffeeTypeRepository extends JpaRepository<CoffeeType, Long> {
    Optional<CoffeeType> findByName(String name);
}
