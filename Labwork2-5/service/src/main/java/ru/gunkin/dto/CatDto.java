package ru.gunkin.dto;

import lombok.Data;
import ru.gunkin.models.cat.Breed;
import ru.gunkin.models.cat.Color;

import java.time.LocalDate;

@Data
public class CatDto {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Breed breed;
    private Color color;

    public CatDto(){}

    public CatDto(long id, String name, LocalDate birthDate, Breed breed, Color color) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }

    public CatDto(String name, LocalDate birthDate, Breed breed, Color color) {
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
    }
}
