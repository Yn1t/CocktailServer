package com.example.cocktailserver.database.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "rating", schema = "public")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;

    private int rate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cocktail")
    private Cocktail cocktail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cocktail_user")
    private CocktailUser cocktail_user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(id, rating.id) && Objects.equals(cocktail.getId(), rating.cocktail.getId())
                && Objects.equals(cocktail_user.getId(), rating.cocktail_user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cocktail.getId(), cocktail_user.getId());
    }
}
