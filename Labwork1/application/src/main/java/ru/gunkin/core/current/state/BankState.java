package ru.gunkin.core.current.state;

import ru.gunkin.contracts.current.state.CurrentBank;
import ru.gunkin.contracts.current.state.CurrentState;
import ru.gunkin.model.Bank;

import java.util.UUID;

public class BankState implements CurrentState, CurrentBank {
    private final Bank _bank;

    public BankState(Bank bank) {
        _bank = bank;
    }

    @Override
    public Bank getBank() {
        return _bank;
    }

    @Override
    public CurrentState moveBack() {
        return new InitialState();
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
        return new AdminState(_bank);
    }

    @Override
    public CurrentState moveToUserState(UUID userId) {
        return new UserState(userId, _bank);
    }

    @Override
    public CurrentState moveToAccountState(UUID accountId) {
        return this;
    }

    @Override
    public CurrentState moveToAccountCreatorState() {
        return this;
    }
}
