package com.example.cocktailserver.controllers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CocktailRequest {
    private String name;
    private String subname;

    private int year;
    private String source;
    private String recipe;
    private String garnish;
    private String history;

    private String period;
    private String style;
    private String cooking;
    private String base;
}
