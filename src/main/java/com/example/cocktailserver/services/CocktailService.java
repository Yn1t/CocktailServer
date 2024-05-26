package com.example.cocktailserver.services;

import com.example.cocktailserver.controllers.exceptions.*;
import com.example.cocktailserver.controllers.models.CocktailRequest;

public interface CocktailService {
    public String createCocktail(CocktailRequest request) throws BaseNotExistException, StyleNotExistException,
            CookingNotExistException, PeriodNotExistException, CocktailAlreadyExistException;
}
