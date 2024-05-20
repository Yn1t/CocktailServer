package com.example.cocktailserver.database.repositories;

import com.example.cocktailserver.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findOptionalByEmail(String email);
}
