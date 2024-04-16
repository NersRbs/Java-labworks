package ru.gunkin.mapers;

import org.mapstruct.Mapper;
import ru.gunkin.dto.CatDto;
import ru.gunkin.models.cat.Cat;

@Mapper(componentModel = "spring")
public interface CatMapper extends Mappable<Cat, CatDto> {
}
