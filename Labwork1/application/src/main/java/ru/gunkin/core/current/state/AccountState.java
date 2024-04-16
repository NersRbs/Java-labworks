package ru.gunkin.core.current.state;

import ru.gunkin.contracts.current.state.CurrentAccount;
import ru.gunkin.contracts.current.state.CurrentBank;
import ru.gunkin.contracts.current.state.CurrentState;
import ru.gunkin.contracts.current.state.CurrentUser;
import ru.gunkin.model.Bank;

import java.util.UUID;

public class AccountState implements CurrentState, CurrentBank, CurrentUser, CurrentAccount {
    private final UUID _userId;
    private final UUID _accountId;
    private final Bank _bank;

    public AccountState(UUID userId, UUID accountId, Bank bank) {
        _userId = userId;
        _accountId = accountId;
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

    @Override
    public UUID getAccountId() {
        return _accountId;
    }
}
