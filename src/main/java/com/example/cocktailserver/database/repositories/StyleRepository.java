package com.example.cocktailserver.database.repositories;

import com.example.cocktailserver.database.entities.Ingredient;
import com.example.cocktailserver.database.entities.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<Style, String> {
    Optional<Style> findOptionalByName(String name);

    @Override
    Optional<Style> findById(String id);

    @Override
    boolean existsById(String id);
}
