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
@Table(name = "style", schema = "public")
public class Style {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "style")
    private List<Cocktail> cocktails;

    private String name;
    private String glass;
    private String icon_link;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Style style = (Style) o;
        return Objects.equals(id, style.id) && Objects.equals(name, style.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
