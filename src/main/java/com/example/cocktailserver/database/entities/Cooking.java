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
@Table(name = "cooking", schema = "public")
public class Cooking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cooking")
    private List<Cocktail> cocktails;

    private String name;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cooking cooking = (Cooking) o;
        return Objects.equals(id, cooking.id) && Objects.equals(name, cooking.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
