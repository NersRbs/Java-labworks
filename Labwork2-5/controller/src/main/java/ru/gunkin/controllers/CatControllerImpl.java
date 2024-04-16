package ru.gunkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gunkin.contracts.CatController;
import ru.gunkin.contracts.CatServices;
import ru.gunkin.dto.CatDto;
import ru.gunkin.dto.OwnerDto;
import ru.gunkin.models.cat.Breed;
import ru.gunkin.models.cat.Color;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cats")
public class CatControllerImpl implements CatController {
    private final CatServices catServices;

    @Autowired
    public CatControllerImpl(CatServices catServices) {
        this.catServices = catServices;
    }

    @Override
    @PostMapping
    public CatDto addCat(@RequestBody CatDto catDto) {
        return catServices.addCat(catDto);
    }

    @Override
    @PatchMapping("/{id}")
    public void updateCat(long id, @RequestBody CatDto catDto) {
        catDto.setId(id);
        catServices.update(catDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable("id") long id) {
        catServices.deleteCat(id);
    }

    @Override
    @PostMapping("/{cat_id}/friends")
    public void addCatFriend(@PathVariable("cat_id") long cat_id, @RequestParam("friend_id") long friend_id) {
        catServices.addFriend(cat_id, friend_id);
    }

    @Override
    @DeleteMapping("/{cat_id}/friends")
    public void removeCatFriend(@PathVariable("cat_id") long cat_id, @RequestParam("friend_id") long friend_id) {
        catServices.removeFriend(cat_id, friend_id);
    }

    @Override
    @GetMapping("/{id}")
    public CatDto getCat(@PathVariable("id") long id) {
        return catServices.findCat(id).orElse(null);
    }

    @Override
    public OwnerDto getCatOwner(long id) {
        return catServices.findOwner(id).orElse(null);
    }

    @Override
    @GetMapping("/{id}/friends")
    public List<CatDto> getCatFriends(@PathVariable("id") long id) {
        return catServices.findFriends(id);
    }

    @Override
    @GetMapping
    public List<CatDto> getCats() {
        return catServices.findAllCats();
    }

    @Override
    public List<CatDto> getCatsByColor(Color color) {
        return catServices.findByColor(color);
    }

    @Override
    public List<CatDto> getCatsByBreed(Breed breed) {
        return catServices.findByBreed(breed);
    }
}
