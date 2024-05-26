package com.example.cocktailserver.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "cocktail_ingredient", schema = "public")
public class CocktailIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cocktail")
    private Cocktail cocktail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ingredient")
    private Ingredient ingredient;

    private float quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CocktailIngredient cocktailIngredient = (CocktailIngredient) o;
        return Objects.equals(id, cocktailIngredient.id) && Objects.equals(cocktail.getId(), cocktailIngredient.cocktail.getId())
                && Objects.equals(cocktail.getSubname(), cocktailIngredient.cocktail.getSubname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cocktail.getName(), cocktail.getSubname());
    }
}
