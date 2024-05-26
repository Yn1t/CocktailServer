package com.example.cocktailserver.database.repositories;

import com.example.cocktailserver.database.entities.CocktailIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CocktailIngredientRepository extends JpaRepository<CocktailIngredient, String> {

    @Override
    Optional<CocktailIngredient> findById(String userId);

    @Override
    boolean existsById(String id);
}
