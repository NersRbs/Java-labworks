package ru.gunkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gunkin.contracts.OwnerController;
import ru.gunkin.contracts.OwnerServices;
import ru.gunkin.dto.CatDto;
import ru.gunkin.dto.OwnerDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnerControllerImpl implements OwnerController {
    private final OwnerServices ownerServices;

    @Autowired
    public OwnerControllerImpl(OwnerServices ownerServices) {
        this.ownerServices = ownerServices;
    }


    @Override
    @PostMapping
    public OwnerDto addOwner(@RequestBody OwnerDto ownerDto) {
        return ownerServices.addOwner(ownerDto);
    }

    @Override
    @PostMapping("/{owner_id}/cats")
    public void addCat(@PathVariable("owner_id") long owner_id, @RequestParam("cat_id") long cat_id) {
        ownerServices.addCat(owner_id, cat_id);
    }

    @Override
    @PatchMapping("/{id}")
    public void updateOwner(@PathVariable("id") long id, @RequestBody OwnerDto ownerDto) {
        ownerDto.setId(id);
        ownerServices.updateOwner(ownerDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable("id") long id) {
        ownerServices.deleteOwner(id);
    }

    @Override
    @DeleteMapping("/{owner_id}/cats")
    public void deleteCat(@PathVariable("owner_id") long owner_id, @RequestParam("cat_id") long cat_id) {
        ownerServices.deleteCat(owner_id, cat_id);
    }

    @Override
    @GetMapping("/{id}")
    public OwnerDto getOwner(@PathVariable("id") long id) {
        return ownerServices.findOwner(id).orElse(null);
    }

    @Override
    @GetMapping("/{id}/cats")
    public List<CatDto> getOwnerCats(@PathVariable("id") long id) {
        return ownerServices.findOwnerCats(id);
    }

    @Override
    @GetMapping
    public List<OwnerDto> getOwners() {
        return ownerServices.findAllOwners();
    }
}
