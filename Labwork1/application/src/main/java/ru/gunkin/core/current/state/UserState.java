package ru.gunkin.core.current.state;

import ru.gunkin.contracts.current.state.CurrentBank;
import ru.gunkin.contracts.current.state.CurrentState;
import ru.gunkin.contracts.current.state.CurrentUser;
import ru.gunkin.model.Bank;

import java.util.UUID;

public class UserState implements CurrentState, CurrentUser, CurrentBank {
    private final UUID _userId;
    private final Bank _bank;

    public UserState(UUID userId, Bank bank) {
        _userId = userId;
        _bank = bank;
    }

    @Override
    public CurrentState moveBack() {
        return new BankState(_bank);
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
        return new AccountState(_userId, accountId, _bank);
    }


    @Override
    public CurrentState moveToAccountCreatorState() {
        return new AccountCreatorState(_userId, _bank);
    }

    @Override
    public UUID getUserId() {
        return _userId;
    }

    @Override
    public Bank getBank() {
        return _bank;
    }
}
