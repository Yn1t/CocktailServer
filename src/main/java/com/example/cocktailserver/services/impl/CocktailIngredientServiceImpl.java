package com.example.cocktailserver.services.impl;

import com.example.cocktailserver.controllers.exceptions.CocktailNotExistsException;
import com.example.cocktailserver.controllers.exceptions.IngredientNotExistsException;
import com.example.cocktailserver.controllers.models.CocktailIngredientRequest;
import com.example.cocktailserver.database.entities.Cocktail;
import com.example.cocktailserver.database.entities.CocktailIngredient;
import com.example.cocktailserver.database.entities.Ingredient;
import com.example.cocktailserver.database.repositories.CocktailIngredientRepository;
import com.example.cocktailserver.database.repositories.CocktailRepository;
import com.example.cocktailserver.database.repositories.IngredientRepository;
import com.example.cocktailserver.services.CocktailIngredientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CocktailIngredientServiceImpl implements CocktailIngredientService {

    private final CocktailRepository cocktailRepository;
    private final IngredientRepository ingredientRepository;
    private final CocktailIngredientRepository cocktailIngredientRepository;
    private final ModelMapper mapper;

    public CocktailIngredientServiceImpl(CocktailRepository cocktailRepository, IngredientRepository ingredientRepository
            ,CocktailIngredientRepository cocktailIngredientRepository, ModelMapper mapper) {
        this.cocktailRepository = cocktailRepository;
        this.cocktailIngredientRepository = cocktailIngredientRepository;
        this.ingredientRepository = ingredientRepository;
        this.mapper = mapper;
    }

    @Override
    public String addIngredientToCocktail(CocktailIngredientRequest request) throws CocktailNotExistsException, IngredientNotExistsException {
        Optional<Cocktail> existedCocktail = cocktailRepository.findById(request.getCocktailId());
        Optional<Ingredient> existedIngredient = ingredientRepository.findById(request.getIngredientId());
        existedCocktail.orElseThrow(CocktailNotExistsException::new);
        existedIngredient.orElseThrow(IngredientNotExistsException::new);


        return null;
    }
}
