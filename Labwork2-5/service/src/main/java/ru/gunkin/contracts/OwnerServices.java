package ru.gunkin.contracts;

import ru.gunkin.dto.CatDto;
import ru.gunkin.dto.OwnerDto;

import java.util.List;
import java.util.Optional;

public interface OwnerServices {
    OwnerDto addOwner(OwnerDto ownerDto);

    void addCat(long ownerId, long catId);

    void updateOwner(OwnerDto ownerDto);

    void deleteOwner(long id);

    void deleteCat(long ownerId, long catId);

    Optional<OwnerDto> findOwner(long id);

    List<OwnerDto> findAllOwners();

    List<CatDto> findOwnerCats(long id);
}
