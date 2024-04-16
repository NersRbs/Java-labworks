package ru.gunkin.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OwnerDto {
    private Long id;
    private String name;
    private LocalDate birthDate;

    public OwnerDto(){}

    public OwnerDto(long id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public OwnerDto(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}
