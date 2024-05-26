package com.example.cocktailserver.database.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "ingredient", schema = "public")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ingredient")
    private List<CocktailIngredient> cocktails;

    private String name;
    private String color = "";
    private String flavour = "";

    private double strength = 0;

    private double sweetness = 0;
    private double sourness = 0;
    private double bitterness = 0;
    private double saltiness = 0;
    private double spiciness = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient ingredient = (Ingredient) o;
        return Objects.equals(id, ingredient.id) && Objects.equals(name, ingredient.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
