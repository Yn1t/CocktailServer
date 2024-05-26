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
@Table(name = "base", schema = "public")
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "base")
    private List<Cocktail> cocktails;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return Objects.equals(id, base.id) && Objects.equals(name, base.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
