package com.example.cocktailserver.database.repositories;

import com.example.cocktailserver.database.entities.Cocktail;
import com.example.cocktailserver.database.entities.CocktailUser;
import com.example.cocktailserver.database.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {
    Optional<Rating> findOptionalByCocktailAndCocktailUser(Cocktail cocktail, CocktailUser cocktailUser);

    @Override
    Optional<Rating> findById(String id);

    @Override
    boolean existsById(String id);
}
