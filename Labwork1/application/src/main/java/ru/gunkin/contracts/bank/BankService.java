package ru.gunkin.contracts.bank;

import ru.gunkin.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankService {
    ResultBankOperation login(UUID id);

    void loginAdmin();

    ResultBankOperation createUser(String firstName, String lastName, Optional<String> address, Optional<Integer> passportNumber);
    ResultBankOperation deleteUser(UUID id);
    ResultBankOperation deleteAndUndoOperation(UUID id);

    void notify(String header, String message);

    ResultBankOperation addSubscriber(UUID id);

    ResultBankOperation removeSubscriber(UUID id);

    String[] getUsersInformation();

    String[] getOperationsInformation();
}
