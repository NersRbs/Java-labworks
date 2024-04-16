import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gunkin.abstractions.CatsRepository;
import ru.gunkin.contracts.CatServices;
import ru.gunkin.controllers.CatControllerImpl;
import ru.gunkin.dto.CatDto;
import ru.gunkin.mapers.CatMapper;
import ru.gunkin.mapers.CatMapperImpl;
import ru.gunkin.mapers.OwnerMapper;
import ru.gunkin.mapers.OwnerMapperImpl;
import ru.gunkin.models.Owner;
import ru.gunkin.models.cat.Breed;
import ru.gunkin.models.cat.Cat;
import ru.gunkin.models.cat.Color;
import ru.gunkin.services.CatServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CatControllerTests {

    private CatControllerImpl catController;
    private HashMap<Long, Cat> cats;

    @BeforeEach
    void setUp() {
        CatsRepository catsRepository = mock(CatsRepository.class);
        CatMapper catMapper = new CatMapperImpl();
        OwnerMapper ownerMapper = new OwnerMapperImpl();

        CatServices catServices = new CatServicesImpl(catsRepository, catMapper, ownerMapper);
        catController = new CatControllerImpl(catServices);
        cats = new HashMap<>();


        doAnswer(invocation -> {
            Cat cat = invocation.getArgument(0);
            cats.put(cat.getId(), cat);
            return null;
        }).when(catsRepository).save(any(Cat.class));

        doAnswer(invocation -> {
            Cat cat = invocation.getArgument(0);
            cats.put(cat.getId(), cat);
            return null;
        }).when(catsRepository).save(any(Cat.class));

        doAnswer(invocation -> {
            Cat cat = invocation.getArgument(0);
            cats.remove(cat.getId());
            return null;
        }).when(catsRepository).delete(any(Cat.class));

        doAnswer(invocation -> {
            long id = invocation.getArgument(0);
            return Optional.ofNullable(cats.get(id));
        }).when(catsRepository).findById(anyLong());

        doAnswer(invocation -> {
            return new ArrayList<>(cats.values());
        }).when(catsRepository).findAll();

    }

    @Test
    void addTest() {
        // Arrange
        CatDto catDto = new CatDto("Barsik", LocalDate.now(), Breed.ABYSSINIAN, Color.BLACK);

        // Act
        catController.addCat(catDto);

        // Assert
        assert cats.size() == 1;
        cats.clear();
    }

    @Test
    void deleteTest() {
        // Arrange
        var cat = new Cat("Barsik", LocalDate.now(), Breed.ABYSSINIAN, Color.BLACK);
        cat.setFriends(new ArrayList<>());

        // Act
        cat.setId(0L);
        cats.put(0L, cat);
        catController.deleteCat(0L);

        // Assert
        assert cats.isEmpty();
    }

    @Test
    void addFriendTest() {
        // Arrange
        Cat cat = new Cat("Barsik", LocalDate.now(), Breed.ABYSSINIAN, Color.BLACK);
        Cat friend = new Cat("Murzik", LocalDate.now(), Breed.ABYSSINIAN, Color.BLACK);

        // Act
        cat.setFriends(new ArrayList<>());
        friend.setFriends(new ArrayList<>());

        cats.put(0L, cat);
        cats.put(1L, friend);

        catController.addCatFriend(0L, 1L);

        // Assert
        assert cat.getFriends().contains(friend);
        assert friend.getFriends().contains(cat);
        cats.clear();
    }

    @Test
    void removeFriendTest() {
        // Arrange
        Cat cat = new Cat("Barsik", LocalDate.now(), Breed.ABYSSINIAN, Color.BLACK);
        Cat friend = new Cat("Murzik", LocalDate.now(), Breed.ABYSSINIAN, Color.BLACK);

        // Act
        cat.setFriends(new ArrayList<>());
        friend.setFriends(new ArrayList<>());

        cat.getFriends().add(friend);
        friend.getFriends().add(cat);

        cats.put(0L, cat);
        cats.put(1L, friend);

        catController.removeCatFriend(0L, 1L);

        // Assert
        assert !cat.getFriends().contains(friend);
        assert !friend.getFriends().contains(cat);
        cats.clear();
    }

    @Test
    void getCatTest() {
        // Arrange
        Cat cat = new Cat("Barsik", LocalDate.now(), Breed.ABYSSINIAN, Color.BLACK);

        // Act
        cat.setId(0L);
        cats.put(0L, cat);
        var catDto = catController.getCat(0L);

        // Assert
        assert catDto.getName().equals("Barsik");
        assert catDto.getBreed().equals(Breed.ABYSSINIAN);
        assert catDto.getColor().equals(Color.BLACK);
        cats.clear();
    }

    @Test
    void getCatOwnerTest() {
        // Arrange
        Cat cat = new Cat("Barsik", LocalDate.now(), Breed.ABYSSINIAN, Color.BLACK);
        Owner owner = new Owner("Vasya", LocalDate.now());

        // Act
        owner.setId(0L);
        cat.setOwner(owner);
        cat.setId(0L);
        cats.put(0L, cat);
        var ownerDto = catController.getCatOwner(0L);

        // Assert
        assert ownerDto.getName().equals("Vasya");
        cats.clear();
    }

    @Test
    void getCats() {
        // Arrange
        Cat cat1 = new Cat("Barsik", LocalDate.now(), Breed.ABYSSINIAN, Color.BLACK);
        Cat cat2 = new Cat("Murzik", LocalDate.now(), Breed.ABYSSINIAN, Color.BLACK);

        // Act
        cat1.setId(0L);
        cat2.setId(1L);
        cats.put(0L, cat1);
        cats.put(1L, cat2);
        var catDtos = catController.getCats();

        // Assert
        assert catDtos.size() == 2;
        cats.clear();
    }

    @Test
    void getCatFriends() {
        // Arrange
        Cat cat = new Cat("Barsik", LocalDate.now(), Breed.ABYSSINIAN, Color.BLACK);
        Cat friend = new Cat("Murzik", LocalDate.now(), Breed.ABYSSINIAN, Color.BLACK);

        // Act
        cat.setId(0L);
        friend.setId(1L);

        cat.setFriends(new ArrayList<>());
        friend.setFriends(new ArrayList<>());

        cat.getFriends().add(friend);
        friend.getFriends().add(cat);

        cats.put(0L, cat);
        cats.put(1L, friend);

        var catDtos = catController.getCatFriends(0L);

        // Assert
        assert catDtos.size() == 1;
        cats.clear();
    }
}