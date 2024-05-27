package com.example.cocktailserver.controllers;

import com.example.cocktailserver.controllers.exceptions.CocktailNotExistsException;
import com.example.cocktailserver.controllers.exceptions.IngredientAlreadyExistException;
import com.example.cocktailserver.controllers.exceptions.IngredientNotExistsException;
import com.example.cocktailserver.controllers.models.CocktailIngredientDto;
import com.example.cocktailserver.controllers.models.CocktailIngredientRequest;
import com.example.cocktailserver.controllers.models.IngredientRequest;
import com.example.cocktailserver.security.models.AuthToken;
import com.example.cocktailserver.services.CocktailIngredientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cocktail_build")
public class CocktailIngredientController {

    CocktailIngredientService cocktailIngredientService;

    public CocktailIngredientController(CocktailIngredientService cocktailIngredientService) {
        this.cocktailIngredientService = cocktailIngredientService;
    }

//    @PostMapping("/add")
//    public String addIngredient(@RequestBody CocktailIngredientRequest params, AuthToken authToken) throws IngredientNotExistsException, CocktailNotExistsException {
//        return cocktailIngredientService.addIngredientToCocktail(params);
//    }
    @PostMapping("/add_ingredients")
    public Map<String, List<CocktailIngredientDto>> addIngredients(@RequestBody Map<String, List<CocktailIngredientRequest>> requestList) throws
            IngredientNotExistsException, CocktailNotExistsException {
        return cocktailIngredientService.addIngredientsToCocktail(requestList.get("ingredients"));
    }
}
