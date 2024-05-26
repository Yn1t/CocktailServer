package com.example.cocktailserver.controllers;


import com.example.cocktailserver.controllers.exceptions.IngredientAlreadyExistException;
import com.example.cocktailserver.controllers.exceptions.IngredientNotExistsException;
import com.example.cocktailserver.controllers.exceptions.UserAlreadyExistException;
import com.example.cocktailserver.controllers.models.IngredientDto;
import com.example.cocktailserver.controllers.models.IngredientRequest;
import com.example.cocktailserver.controllers.models.RegistrationParamsRequest;
import com.example.cocktailserver.controllers.models.UserDto;
import com.example.cocktailserver.security.models.AuthToken;
import com.example.cocktailserver.services.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/ingredient")
public class IngredientController {

    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String addIngredient(@RequestBody IngredientRequest params, AuthToken authToken) throws UnsupportedEncodingException, IngredientAlreadyExistException {
        return service.saveIngredient(params, authToken);
    }

    @GetMapping("/{id}")
    public IngredientDto getIngredient(@PathVariable("id") String ingredientId) throws IngredientNotExistsException {
        return service.getIngredient(ingredientId);
    }
}

