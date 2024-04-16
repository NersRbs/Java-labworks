package ru.gunkin.mapers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.gunkin.dto.CatDto;
import ru.gunkin.models.cat.Cat;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-16T13:33:49+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 20.0.2.1 (Amazon.com Inc.)"
)
@Component
public class CatMapperImpl implements CatMapper {

    @Override
    public CatDto toDto(Cat model) {
        if ( model == null ) {
            return null;
        }

        CatDto catDto = new CatDto();

        catDto.setId( model.getId() );
        catDto.setName( model.getName() );
        catDto.setBirthDate( model.getBirthDate() );
        catDto.setBreed( model.getBreed() );
        catDto.setColor( model.getColor() );

        return catDto;
    }

    @Override
    public List<CatDto> toDto(List<Cat> model) {
        if ( model == null ) {
            return null;
        }

        List<CatDto> list = new ArrayList<CatDto>( model.size() );
        for ( Cat cat : model ) {
            list.add( toDto( cat ) );
        }

        return list;
    }

    @Override
    public Cat toModel(CatDto dto) {
        if ( dto == null ) {
            return null;
        }

        Cat cat = new Cat();

        cat.setId( dto.getId() );
        cat.setName( dto.getName() );
        cat.setBirthDate( dto.getBirthDate() );
        cat.setBreed( dto.getBreed() );
        cat.setColor( dto.getColor() );

        return cat;
    }

    @Override
    public List<Cat> toModel(List<CatDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Cat> list = new ArrayList<Cat>( dto.size() );
        for ( CatDto catDto : dto ) {
            list.add( toModel( catDto ) );
        }

        return list;
    }
}
