package ru.gunkin.contracts.current.state;

import ru.gunkin.model.Bank;

import java.util.UUID;

public interface CurrentState {

    CurrentState moveBack();
    CurrentState moveToInitialState();

    CurrentState moveToCentralBankState();

    CurrentState moveToBankState(Bank bank);

    CurrentState moveToAdminState();

    CurrentState moveToUserState(UUID userId);

    CurrentState moveToAccountState(UUID accountId);

    CurrentState moveToAccountCreatorState();
}
