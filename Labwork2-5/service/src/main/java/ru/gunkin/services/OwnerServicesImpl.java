package ru.gunkin.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.gunkin.abstractions.CatsRepository;
import ru.gunkin.abstractions.OwnersRepository;
import ru.gunkin.contracts.OwnerServices;
import ru.gunkin.dto.CatDto;
import ru.gunkin.dto.OwnerDto;
import ru.gunkin.exceptions.NotFoundException;
import ru.gunkin.mapers.CatMapper;
import ru.gunkin.mapers.OwnerMapper;
import ru.gunkin.models.Owner;
import ru.gunkin.models.cat.Cat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerServicesImpl implements OwnerServices {
    private final OwnersRepository ownersRepository;
    private final CatsRepository catsRepository;
    private final OwnerMapper ownerMapper;
    private final CatMapper catMapper;

    @Autowired
    public OwnerServicesImpl(OwnersRepository ownersRepository, CatsRepository catsRepository, OwnerMapper ownerMapper, CatMapper catMapper) {
        this.ownersRepository = ownersRepository;
        this.catsRepository = catsRepository;
        this.ownerMapper = ownerMapper;
        this.catMapper = catMapper;
    }

    @Override
    public OwnerDto addOwner(OwnerDto ownerDto) {
        return ownerMapper.toDto(ownersRepository.save(ownerMapper.toModel(ownerDto)));
    }

    @Override
    @Transactional
    public void addCat(long ownerId, long catId) {
        Owner owner = ownersRepository.findById(ownerId).orElse(null);
        Cat cat = catsRepository.findById(catId).orElse(null);

        if (owner == null)
            throw new NotFoundException("Owner not found");

        if (cat == null)
            throw new NotFoundException("Cat not found");



        owner.getCats().add(cat);
        cat.setOwner(owner);

        ownersRepository.save(owner);
        catsRepository.save(cat);
    }

    @Override
    @Transactional
    public void updateOwner(OwnerDto ownerDto) {
        if (ownersRepository.existsById(ownerDto.getId()))
            ownersRepository.save(ownerMapper.toModel(ownerDto));
    }

    @Override
    @Transactional
    public void deleteOwner(long id) {
        ownersRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteCat(long ownerId, long catId) {
        Owner owner = ownersRepository.findById(ownerId).orElse(null);
        Cat cat = catsRepository.findById(catId).orElse(null);

        if (owner == null)
            throw new NotFoundException("Owner not found");

        if (cat == null)
            throw new NotFoundException("Cat not found");

        owner.getCats().remove(cat);
        cat.setOwner(null);

        ownersRepository.save(owner);
        catsRepository.save(cat);
    }

    @Override
    @Transactional
    public Optional<OwnerDto> findOwner(long id) {
        Optional<Owner> owner = ownersRepository.findById(id);

        if (owner.isEmpty())
            throw new NotFoundException("Owner not found");

        return owner.map(ownerMapper::toDto);
    }

    @Override
    @Transactional
    public List<OwnerDto> findAllOwners() {
        return ownerMapper.toDto(ownersRepository.findAll());
    }

    @Override
    @Transactional
    public List<CatDto> findOwnerCats(long id) {
        Owner owner = ownersRepository.findById(id).orElse(null);

        if (owner == null)
            throw new NotFoundException("Owner not found");

        return catMapper.toDto(owner.getCats());
    }
}
