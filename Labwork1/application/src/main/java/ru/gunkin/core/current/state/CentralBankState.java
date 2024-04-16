package ru.gunkin.core.current.state;

import ru.gunkin.contracts.current.state.CurrentState;
import ru.gunkin.model.Bank;

import java.util.UUID;

public class CentralBankState implements CurrentState {
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
