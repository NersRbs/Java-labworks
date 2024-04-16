package ru.gunkin.contracts;

import ru.gunkin.dto.CatDto;
import ru.gunkin.dto.OwnerDto;

import java.util.List;

public interface OwnerController {
    OwnerDto addOwner(OwnerDto ownerDto);

    void addCat(long owner_id, long cat_id);

    void updateOwner(long id, OwnerDto ownerDto);

    void deleteOwner(long id);

    void deleteCat(long owner_id, long cat_id);

    OwnerDto getOwner(long id);

    List<CatDto> getOwnerCats(long id);

    List<OwnerDto> getOwners();
}
