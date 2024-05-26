package com.example.cocktailserver.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "cocktail", schema = "public")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;

    private String name;
    private String subname;
    private String picture_link;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cocktail")
    private List<CocktailIngredient> cocktailIngredients;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cocktail")
    private List<Rating> ratings;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cooking", nullable=false)
    private Cooking cooking;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="period", nullable=false)
    private Period period;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="style", nullable=false)
    private Style style;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="base", nullable=false)
    private Base base;

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

    private int views;
    private int rating_amount;
    private float rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cocktail cocktail = (Cocktail) o;
        return Objects.equals(id, cocktail.id) && Objects.equals(name, cocktail.name)
                && Objects.equals(subname, cocktail.subname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, subname);
    }
}
