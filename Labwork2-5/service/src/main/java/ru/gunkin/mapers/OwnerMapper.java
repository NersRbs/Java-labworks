package ru.gunkin.mapers;

import org.mapstruct.Mapper;
import ru.gunkin.dto.OwnerDto;
import ru.gunkin.models.Owner;

@Mapper(componentModel = "spring")
public interface OwnerMapper extends Mappable<Owner, OwnerDto> {
}