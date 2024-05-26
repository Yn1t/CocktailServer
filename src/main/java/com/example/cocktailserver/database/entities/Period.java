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
@Table(name = "period", schema = "public")
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private String id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "period")
    private List<Cocktail> cocktails;

    private String name;
    private int start_;
    private int end_;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(id, period.id) && Objects.equals(name, period.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
