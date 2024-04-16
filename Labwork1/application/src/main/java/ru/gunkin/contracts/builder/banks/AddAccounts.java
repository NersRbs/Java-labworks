package ru.gunkin.contracts.builder.banks;

import ru.gunkin.contracts.account.Account;

import java.util.HashMap;
import java.util.UUID;

public interface AddAccounts {
    AddOperations addAccounts(HashMap<UUID, Account> accounts);
}
