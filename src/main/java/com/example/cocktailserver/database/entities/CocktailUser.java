package com.example.cocktailserver.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "cocktail_user", schema = "public")
public class CocktailUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime addDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cocktail_user")
    private List<Rating> ratings;

    private String name;
    private String email;
    private String password;

    private double pref_strength = 0.0;

    private double pref_sweetness = 0.2;
    private double pref_sourness = 0.2;
    private double pref_bitterness = 0.2;
    private double pref_saltiness = 0.2;
    private double pref_spiciness = 0.2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CocktailUser cocktailUser = (CocktailUser) o;
        return Objects.equals(id, cocktailUser.id) && Objects.equals(addDate, cocktailUser.addDate)
                && Objects.equals(name, cocktailUser.name) && email.equals(cocktailUser.email) && email.equals(cocktailUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addDate, email, password);
    }
}
