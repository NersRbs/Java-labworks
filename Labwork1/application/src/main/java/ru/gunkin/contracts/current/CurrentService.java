package ru.gunkin.contracts.current;

import ru.gunkin.contracts.current.state.CurrentState;
import ru.gunkin.model.Bank;

import java.util.Optional;
import java.util.UUID;

public interface CurrentService {

    CurrentState getState();

    void selectBack();

    void selectInitial();

    void selectCentralBank();

    void selectBank(Bank bank);

    void selectAdmin();

    void selectUser(UUID userId);

    void selectAccount(UUID accountId);

    void selectAccountCreator();

    Optional<Bank> tryGetBank();

    Optional<UUID> tryGetUserId();

    Optional<UUID> tryGetAccountId();
}
