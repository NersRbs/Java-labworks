package ru.gunkin.models.cat;

import jakarta.persistence.*;
import lombok.Data;
import ru.gunkin.models.Owner;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Breed breed;
    private Color color;
    @ManyToOne
    private Owner owner;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Cat> friends;

    public Cat(){}


    public Cat(String name, LocalDate birthDate, Breed breed, Color color) {
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }
}
