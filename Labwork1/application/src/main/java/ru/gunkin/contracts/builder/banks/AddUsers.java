package ru.gunkin.contracts.builder.banks;

import ru.gunkin.model.User;

import java.util.HashMap;
import java.util.UUID;

public interface AddUsers {
    AddAccounts addUsers(HashMap<UUID, User> users);
}
