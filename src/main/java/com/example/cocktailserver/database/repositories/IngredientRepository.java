package com.example.cocktailserver.database.repositories;

import com.example.cocktailserver.database.entities.Cooking;
import com.example.cocktailserver.database.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    Optional<Ingredient> findOptionalByName(String name);

    @Override
    Optional<Ingredient> findById(String id);

    @Override
    boolean existsById(String id);
}
