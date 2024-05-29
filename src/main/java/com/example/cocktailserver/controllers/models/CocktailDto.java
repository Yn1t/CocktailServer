package com.example.cocktailserver.controllers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocktailDto {
    private String id;
    private String name;
    private String subname;
    private String picture_link;

    private double strength = 0;
    private double sweetness = 0;
    private double sourness = 0;
    private double bitterness = 0;
    private double saltiness = 0;
    private double spiciness = 0;

    private int year;
    private String source;
    private String recipe;
    private String garnish;
    private String history;

    private int rating;
}
