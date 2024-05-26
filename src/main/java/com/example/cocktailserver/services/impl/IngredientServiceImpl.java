package com.example.cocktailserver.services.impl;

import com.example.cocktailserver.controllers.exceptions.IngredientAlreadyExistException;
import com.example.cocktailserver.controllers.exceptions.IngredientNotExistsException;
import com.example.cocktailserver.controllers.models.IngredientDto;
import com.example.cocktailserver.controllers.models.IngredientRequest;
import com.example.cocktailserver.database.entities.Ingredient;
import com.example.cocktailserver.database.repositories.IngredientRepository;
import com.example.cocktailserver.security.models.AuthToken;
import com.example.cocktailserver.services.IngredientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final ModelMapper mapper;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, ModelMapper mapper) {
        this.ingredientRepository = ingredientRepository;
        this.mapper = mapper;
    }

    @Override
    public String saveIngredient(IngredientRequest request, AuthToken authToken) throws UnsupportedEncodingException, IngredientAlreadyExistException {
        Optional<Ingredient> existedIngredient = ingredientRepository.findOptionalByName(request.getName());

        if (existedIngredient.isPresent()) {
            throw new IngredientAlreadyExistException();
        }

        Ingredient new_ingredient = mapper.map(request, Ingredient.class);

        ingredientRepository.save(new_ingredient);
        return new_ingredient.getId();
    }

    @Override
    public IngredientDto getIngredient(String id) throws IngredientNotExistsException {
        Optional<Ingredient> existedIngredient = ingredientRepository.findById(id);
        Ingredient ingredient = existedIngredient.orElseThrow(IngredientNotExistsException::new);

        return mapper.map(ingredient, IngredientDto.class);
    }
}
