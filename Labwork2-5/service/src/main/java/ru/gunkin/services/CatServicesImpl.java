package ru.gunkin.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gunkin.abstractions.CatsRepository;
import ru.gunkin.contracts.CatServices;
import ru.gunkin.dto.CatDto;
import ru.gunkin.dto.OwnerDto;
import ru.gunkin.exceptions.NotFoundException;
import ru.gunkin.mapers.CatMapper;
import ru.gunkin.mapers.OwnerMapper;
import ru.gunkin.models.cat.Breed;
import ru.gunkin.models.cat.Cat;
import ru.gunkin.models.cat.Color;

import java.util.List;
import java.util.Optional;


@Service
public class CatServicesImpl implements CatServices {
    private final CatsRepository catsRepository;
    private final CatMapper catMapper;
    private final OwnerMapper ownerMapper;

    @Autowired
    public CatServicesImpl(CatsRepository catsRepository, CatMapper catMapper, OwnerMapper ownerMapper) {
        this.catsRepository = catsRepository;
        this.catMapper = catMapper;
        this.ownerMapper = ownerMapper;
    }

    @Override
    @Transactional
    public CatDto addCat(CatDto catDto) {
        Cat cat = catMapper.toModel(catDto);
        return catMapper.toDto(catsRepository.save(cat));
    }

    @Override
    @Transactional
    public void update(CatDto catDto) {
        if (!catsRepository.existsById(catDto.getId()))
            throw new NotFoundException("Cat not found");

        catsRepository.save(catMapper.toModel(catDto));
    }

    @Override
    @Transactional
    public void addFriend(long catId, long friendId) {
        Cat cat = catsRepository.findById(catId).orElse(null);
        Cat friend = catsRepository.findById(friendId).orElse(null);

        if (cat == null)
            throw new NotFoundException("Cat not found");

        if (friend == null)
            throw new NotFoundException("Friend not found");

        cat.getFriends().add(friend);
        friend.getFriends().add(cat);

        catsRepository.save(cat);
        catsRepository.save(friend);
    }

    @Override
    @Transactional
    public void removeFriend(long catId, long friendId) {
        Cat cat = catsRepository.findById(catId).orElse(null);
        Cat friend = catsRepository.findById(friendId).orElse(null);

        if (cat == null)
            throw new NotFoundException("Cat not found");

        if (friend == null)
            throw new NotFoundException("Friend not found");

        cat.getFriends().remove(friend);
        friend.getFriends().remove(cat);

        catsRepository.save(cat);
        catsRepository.save(friend);
    }

    @Override
    @Transactional
    public void deleteCat(long id) {
        Cat cat = catsRepository.findById(id).orElse(null);

        if (cat == null)
            throw new NotFoundException("Cat not found");

        for (Cat friend : cat.getFriends()) {
            friend.getFriends().remove(cat);
            catsRepository.save(friend);
        }

        catsRepository.delete(cat);
    }

    @Override
    @Transactional
    public Optional<CatDto> findCat(long id) {
        Cat cat = catsRepository.findById(id).orElse(null);

        if (cat == null)
            throw new NotFoundException("Cat not found");

        return Optional.of(catMapper.toDto(cat));
    }

    @Override
    @Transactional
    public Optional<OwnerDto> findOwner(long id) {
        Cat cat = catsRepository.findById(id).orElse(null);

        if (cat == null)
            throw new NotFoundException("Cat not found");

        if (cat.getOwner() == null)
            return Optional.empty();

        return Optional.of(ownerMapper.toDto(cat.getOwner()));
    }

    @Override
    @Transactional
    public List<CatDto> findAllCats() {
        return catMapper.toDto(catsRepository.findAll());
    }

    @Override
    @Transactional
    public List<CatDto> findFriends(long id) {
        Cat cat = catsRepository.findById(id).orElse(null);

        if (cat == null)
            throw new NotFoundException("Cat not found");

        return catMapper.toDto(cat.getFriends());
    }

    @Override
    @Transactional
    public List<CatDto> findByColor(Color color) {
        return catMapper.toDto(catsRepository.findCatByColor(color));
    }

    @Override
    @Transactional
    public List<CatDto> findByBreed(Breed breed) {
        return catMapper.toDto(catsRepository.findCatByBreed(breed));
    }
}
