package com.example.cocktailserver.database.repositories;

import com.example.cocktailserver.database.entities.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, String> {
    Optional<Cocktail> findOptionalById(String id);

    boolean existsById(String id);
}

