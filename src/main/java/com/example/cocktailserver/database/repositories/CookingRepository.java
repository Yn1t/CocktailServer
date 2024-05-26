package com.example.cocktailserver.database.repositories;

import com.example.cocktailserver.database.entities.Cooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CookingRepository extends JpaRepository<Cooking, String> {

    Optional<Cooking> findOptionalByName(String name);

    @Override
    Optional<Cooking> findById(String id);

    @Override
    boolean existsById(String id);
}
