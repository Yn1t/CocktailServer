package com.example.cocktailserver.services;

import com.example.cocktailserver.controllers.exceptions.*;
import com.example.cocktailserver.controllers.models.CocktailDto;
import com.example.cocktailserver.controllers.models.CocktailRequest;

public interface CocktailService {
    public String createCocktail(CocktailRequest request) throws BaseNotExistException, StyleNotExistException,
            CookingNotExistException, PeriodNotExistException, CocktailAlreadyExistException;
    public CocktailDto getCocktail(String id) throws CocktailNotExistsException;
}
