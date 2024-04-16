package ru.gunkin.contracts.builder.users;

import ru.gunkin.model.User;

import java.util.Optional;

public interface UserBuilder {

    UserBuilder addAddress(Optional<String> address);
    UserBuilder addPassportNumber(Optional<Integer> passportNumber);
    User build();
}
