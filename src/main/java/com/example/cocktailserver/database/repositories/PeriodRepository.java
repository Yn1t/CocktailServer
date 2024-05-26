package com.example.cocktailserver.database.repositories;

import com.example.cocktailserver.database.entities.Ingredient;
import com.example.cocktailserver.database.entities.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeriodRepository extends JpaRepository<Period, String> {
    Optional<Period> findOptionalByName(String name);

    @Override
    Optional<Period> findById(String id);

    @Override
    boolean existsById(String id);
}
