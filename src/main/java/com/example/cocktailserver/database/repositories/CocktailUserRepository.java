package com.example.cocktailserver.database.repositories;

import com.example.cocktailserver.database.entities.CocktailUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CocktailUserRepository extends JpaRepository<CocktailUser, String> {

    Optional<CocktailUser> findOptionalByEmail(String email);

    @Override
    Optional<CocktailUser> findById(String id);
    @Override
    boolean existsById(String id);
}
