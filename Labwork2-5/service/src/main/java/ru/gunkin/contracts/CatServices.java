package ru.gunkin.contracts;

import ru.gunkin.dto.CatDto;
import ru.gunkin.dto.OwnerDto;
import ru.gunkin.models.cat.Breed;
import ru.gunkin.models.cat.Color;

import java.util.List;
import java.util.Optional;

public interface CatServices {
    CatDto addCat(CatDto catDto);

    void update(CatDto catDto);

    void addFriend(long catId, long friendId);

    void removeFriend(long catId, long friendId);

    void deleteCat(long id);

    Optional<CatDto> findCat(long id);

    Optional<OwnerDto> findOwner(long id);

    List<CatDto> findAllCats();

    List<CatDto> findFriends(long id);

    List<CatDto> findByColor(Color color);

    List<CatDto> findByBreed(Breed breed);
}
