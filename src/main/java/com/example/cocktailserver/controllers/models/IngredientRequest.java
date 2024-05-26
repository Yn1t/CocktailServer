package com.example.cocktailserver.controllers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRequest {
    private String name;

    private String flavour;
    private String color;

    private double strength;

    private double bitterness;
    private double saltiness;
    private double sourness;
    private double spiciness;
    private double sweetness;
}
