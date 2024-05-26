package com.example.cocktailserver.services;


import com.example.cocktailserver.controllers.exceptions.IngredientAlreadyExistException;
import com.example.cocktailserver.controllers.exceptions.IngredientNotExistsException;
import com.example.cocktailserver.controllers.models.IngredientDto;
import com.example.cocktailserver.controllers.models.IngredientRequest;
import com.example.cocktailserver.security.models.AuthToken;

import java.io.UnsupportedEncodingException;

public interface IngredientService {
    String saveIngredient(IngredientRequest request, AuthToken authToken) throws UnsupportedEncodingException, IngredientAlreadyExistException;
    IngredientDto getIngredient(String id) throws IngredientNotExistsException;
}
