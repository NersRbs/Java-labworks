package ru.gunkin.core.current.state;

import ru.gunkin.contracts.current.state.CurrentState;
import ru.gunkin.model.Bank;

import java.util.UUID;

public class InitialState implements CurrentState {
    @Override
    public CurrentState moveBack() {
        return this;
    }

    @Override
    public CurrentState moveToInitialState() {
        return this;
    }

    @Override
    public CurrentState moveToCentralBankState() {
        return new CentralBankState();
    }

    @Override
    public CurrentState moveToBankState(Bank bank) {
        return new BankState(bank);
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
