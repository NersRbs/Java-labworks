package ru.gunkin.core.current.state;

import ru.gunkin.contracts.current.state.CurrentBank;
import ru.gunkin.contracts.current.state.CurrentState;
import ru.gunkin.model.Bank;

import java.util.UUID;

public class AdminState implements CurrentState, CurrentBank {
    private final Bank _bank;

    public AdminState(Bank bank) {
        _bank = bank;
    }
    @Override
    public Bank getBank() {
        return _bank;
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
        return this;
    }

    @Override
    public CurrentState moveToAccountCreatorState() {
        return this;
    }
}
