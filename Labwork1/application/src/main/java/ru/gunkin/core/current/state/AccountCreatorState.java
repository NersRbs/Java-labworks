package ru.gunkin.core.current.state;

import ru.gunkin.contracts.current.state.CurrentBank;
import ru.gunkin.contracts.current.state.CurrentState;
import ru.gunkin.contracts.current.state.CurrentUser;
import ru.gunkin.model.Bank;

import java.util.UUID;

public class AccountCreatorState implements CurrentState, CurrentBank, CurrentUser {
    private final UUID _userId;
    private final Bank _bank;

    public AccountCreatorState(UUID userId, Bank bank) {
        _userId = userId;
        _bank = bank;
    }

    @Override
    public Bank getBank() {
        return _bank;
    }

    @Override
    public CurrentState moveBack() {
        return new UserState(_userId, _bank);
    }

    @Override
    public CurrentState moveToInitialState() {
        return this;
    }

    @Override
    public CurrentState moveToCentralBankState() {
        return this;
    }

    @Override
    public CurrentState moveToBankState(Bank bank) {
        return this;
    }

    @Override
    public CurrentState moveToAdminState() {
        return this;
    }

    @Override
    public CurrentState moveToUserState(UUID userId) {
        return this;
    }

    @Override
    public CurrentState moveToAccountState(UUID accountId) {
        return this;
    }

    @Override
    public CurrentState moveToAccountCreatorState() {
        return this;
    }

    @Override
    public UUID getUserId() {
        return _userId;
    }
}
