package ru.gunkin.abstractions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gunkin.models.cat.Breed;
import ru.gunkin.models.cat.Cat;
import ru.gunkin.models.cat.Color;

import java.util.List;

@Repository
public interface CatsRepository extends JpaRepository<Cat, Long> {
    List<Cat> findCatByColor(Color color);
    List<Cat> findCatByBreed(Breed breed);
}
