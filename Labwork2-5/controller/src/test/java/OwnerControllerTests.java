import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gunkin.abstractions.CatsRepository;
import ru.gunkin.abstractions.OwnersRepository;
import ru.gunkin.contracts.OwnerServices;
import ru.gunkin.controllers.OwnerControllerImpl;
import ru.gunkin.dto.OwnerDto;
import ru.gunkin.mapers.CatMapper;
import ru.gunkin.mapers.CatMapperImpl;
import ru.gunkin.mapers.OwnerMapper;
import ru.gunkin.mapers.OwnerMapperImpl;
import ru.gunkin.models.Owner;
import ru.gunkin.services.OwnerServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static org.mockito.Mockito.*;

class OwnerControllerTests {

    private OwnerControllerImpl ownerController;
    private HashMap<Long, Owner> owners;

    @BeforeEach
    void setUp() {
        OwnersRepository ownersRepository = mock(OwnersRepository.class);
        CatsRepository catsRepository = mock(CatsRepository.class);
        OwnerMapper ownerMapper = new OwnerMapperImpl();
        CatMapper catMapper = new CatMapperImpl();

        OwnerServices ownerServices = new OwnerServicesImpl(ownersRepository, catsRepository, ownerMapper, catMapper);
        ownerController = new OwnerControllerImpl(ownerServices);
        owners = new HashMap<>();

        doAnswer(invocation -> {
            Owner owner = invocation.getArgument(0);
            owners.put(owner.getId(), owner);
            return null;
        }).when(ownersRepository).save(any(Owner.class));

        doAnswer(invocation -> {
            long id = invocation.getArgument(0);
            owners.remove(id);
            return null;
        }).when(ownersRepository).deleteById(anyLong());

        doAnswer(invocation -> {
            long id = invocation.getArgument(0);
            return Optional.ofNullable(owners.get(id));
        }).when(ownersRepository).findById(anyLong());

        doAnswer(invocation -> {
            return new ArrayList<>(owners.values());
        }).when(ownersRepository).findAll();

    }

    @Test
    void addTest() {
        // Arrange
        OwnerDto ownerDto = new OwnerDto("John", LocalDate.of(1990, 5, 15));

        // Act
        ownerController.addOwner(ownerDto);

        // Assert
        assert owners.size() == 1;
        owners.clear();
    }

    @Test
    void deleteTest() {
        // Arrange
        var owner = new Owner("John", LocalDate.of(1990, 5, 15));

        // Act
        owner.setId(0L);
        owners.put(0L, owner);
        ownerController.deleteOwner(0L);

        // Assert
        assert owners.isEmpty();
    }

    @Test
    void getOwnerTest() {
        // Arrange
        var owner = new Owner("John", LocalDate.of(1990, 5, 15));

        // Act
        owner.setId(0L);
        owners.put(0L, owner);
        var ownerDto = ownerController.getOwner(0L);

        // Assert
        assert ownerDto.getName().equals("John");
        assert ownerDto.getBirthDate().equals(LocalDate.of(1990, 5, 15));
        owners.clear();
    }

    @Test
    void getOwnersTest() {
        // Arrange
        var owner1 = new Owner("John", LocalDate.of(1990, 5, 15));
        var owner2 = new Owner("Jane", LocalDate.of(1985, 8, 20));

        // Act
        owner1.setId(0L);
        owner2.setId(1L);
        owners.put(0L, owner1);
        owners.put(1L, owner2);
        var ownerDtos = ownerController.getOwners();

        // Assert
        assert ownerDtos.size() == 2;
        owners.clear();
    }
}