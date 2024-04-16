package ru.gunkin.contracts;

import ru.gunkin.dto.CatDto;
import ru.gunkin.dto.OwnerDto;
import ru.gunkin.models.cat.Breed;
import ru.gunkin.models.cat.Color;

import java.util.List;


public interface CatController {
    CatDto addCat(CatDto catDto);

    void updateCat(long id, CatDto catDto);

    void deleteCat(long id);

    void addCatFriend(long cat_id, long friend_id);

    void removeCatFriend(long cat_id, long friend_id);

    CatDto getCat(long id);

    OwnerDto getCatOwner(long id);

    List<CatDto> getCatFriends(long id);

    List<CatDto> getCats();

    List<CatDto> getCatsByColor(Color color);

    List<CatDto> getCatsByBreed(Breed breed);
}
