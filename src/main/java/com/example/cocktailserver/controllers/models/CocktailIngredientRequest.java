package com.example.cocktailserver.controllers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocktailIngredientRequest {
    private String ingredientId;
    private String cocktailId;
    private float quantity;
}
