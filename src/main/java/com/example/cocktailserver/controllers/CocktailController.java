package com.example.cocktailserver.controllers;


import com.example.cocktailserver.controllers.exceptions.*;
import com.example.cocktailserver.controllers.models.CocktailRequest;
import com.example.cocktailserver.services.CocktailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cocktail")
public class CocktailController {

    private final CocktailService cocktailService;

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @PostMapping("/create")
    public String createCocktail(@RequestBody CocktailRequest cocktailRequest) throws StyleNotExistException,
            BaseNotExistException, PeriodNotExistException, CookingNotExistException, CocktailAlreadyExistException {
        return cocktailService.createCocktail(cocktailRequest);
    }

}
