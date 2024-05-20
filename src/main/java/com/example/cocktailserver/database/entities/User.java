package com.example.cocktailserver.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32, updatable = false, nullable = false)
    private String id;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime addDate;

    private String name;
    private String email;
    private String password;

    private int pref_strength = 0;
    private int pref_sweetness = 0;
    private int pref_sourness = 0;
    private int pref_bitterness = 0;
    private int pref_saltiness = 0;
    private int pref_spiciness = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(addDate, user.addDate)
                && Objects.equals(name, user.name) && email.equals(user.email) && email.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addDate, email, password);
    }
}
