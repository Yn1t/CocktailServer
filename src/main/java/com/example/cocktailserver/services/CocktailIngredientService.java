package com.example.cocktailserver.services;

import com.example.cocktailserver.controllers.exceptions.CocktailNotExistsException;
import com.example.cocktailserver.controllers.exceptions.IngredientNotExistsException;
import com.example.cocktailserver.controllers.models.CocktailIngredientRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CocktailIngredientService {
    String addIngredientToCocktail(CocktailIngredientRequest request) throws CocktailNotExistsException, IngredientNotExistsException;
}
