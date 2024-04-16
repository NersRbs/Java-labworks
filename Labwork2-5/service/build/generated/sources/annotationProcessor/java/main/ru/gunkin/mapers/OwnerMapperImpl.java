package ru.gunkin.mapers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.gunkin.dto.OwnerDto;
import ru.gunkin.models.Owner;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-16T13:33:49+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 20.0.2.1 (Amazon.com Inc.)"
)
@Component
public class OwnerMapperImpl implements OwnerMapper {

    @Override
    public OwnerDto toDto(Owner model) {
        if ( model == null ) {
            return null;
        }

        OwnerDto ownerDto = new OwnerDto();

        ownerDto.setId( model.getId() );
        ownerDto.setName( model.getName() );
        ownerDto.setBirthDate( model.getBirthDate() );

        return ownerDto;
    }

    @Override
    public List<OwnerDto> toDto(List<Owner> model) {
        if ( model == null ) {
            return null;
        }

        List<OwnerDto> list = new ArrayList<OwnerDto>( model.size() );
        for ( Owner owner : model ) {
            list.add( toDto( owner ) );
        }

        return list;
    }

    @Override
    public Owner toModel(OwnerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Owner owner = new Owner();

        owner.setId( dto.getId() );
        owner.setName( dto.getName() );
        owner.setBirthDate( dto.getBirthDate() );

        return owner;
    }

    @Override
    public List<Owner> toModel(List<OwnerDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Owner> list = new ArrayList<Owner>( dto.size() );
        for ( OwnerDto ownerDto : dto ) {
            list.add( toModel( ownerDto ) );
        }

        return list;
    }
}
