package com.example.cocktailserver.controllers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String name;

    private double pref_strength = 0;

    private double pref_sweetness;
    private double pref_sourness;
    private double pref_bitterness;
    private double pref_saltiness;
    private double pref_spiciness;

    private List<RatingDto> ratings;
    private List<FavoriteDto> favorites;
}
