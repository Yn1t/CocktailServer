package com.example.cocktailserver.database.repositories;

import com.example.cocktailserver.database.entities.Cooking;
import com.example.cocktailserver.database.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, String> {

    @Override
    Optional<Favorite> findById(String id);

    @Override
    boolean existsById(String id);
}
