package com.example.cocktailserver.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "favorite", schema = "public")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cocktail")
    private Cocktail cocktail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cocktail_user")
    private CocktailUser cocktailUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return Objects.equals(id, favorite.id) && Objects.equals(cocktailUser.getId(), favorite.cocktailUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cocktailUser.getId());
    }
}
