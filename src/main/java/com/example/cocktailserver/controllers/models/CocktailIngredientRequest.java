package com.example.cocktailserver.controllers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocktailIngredientRequest {
    private String ingredientName;
    private String cocktailName;
    private String cocktailSubName;
    private Double quantity;
}
