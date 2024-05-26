package com.example.cocktailserver.database.repositories;

import com.example.cocktailserver.database.entities.Base;
import com.example.cocktailserver.database.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseRepository extends JpaRepository<Base, String> {

    Optional<Base> findOptionalByName(String name);

    @Override
    Optional<Base> findById(String userId);

    @Override
    boolean existsById(String id);
}
