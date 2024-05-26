package com.example.cocktailserver.database.repositories;

import com.example.cocktailserver.database.entities.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, String> {
    //Optional<Cocktail> findOptionalByNameAndSubname(String name, String subname);

    @Override
    Optional<Cocktail> findById(String id);

    @Override
    boolean existsById(String id);
}

