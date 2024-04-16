package ru.gunkin.core.current;

import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.current.state.CurrentAccount;
import ru.gunkin.contracts.current.state.CurrentBank;
import ru.gunkin.contracts.current.state.CurrentState;
import ru.gunkin.contracts.current.state.CurrentUser;
import ru.gunkin.core.current.state.InitialState;
import ru.gunkin.model.Bank;

import java.util.Optional;
import java.util.UUID;

public class CurrentServiceImpl implements CurrentService {
    private CurrentState state = new InitialState();

    public CurrentState getState() {
        return state;
    }

    @Override
    public void selectBack() {
        state = state.moveBack();
    }

    @Override
    public void selectInitial() {
        state = state.moveToInitialState();
    }

    @Override
    public void selectCentralBank() {
        state = state.moveToCentralBankState();
    }

    @Override
    public void selectBank(Bank bank) {
        state = state.moveToBankState(bank);
    }


    @Override
    public void selectAdmin() {
        state = state.moveToAdminState();
    }

    @Override
    public void selectUser(UUID userId) {
        state = state.moveToUserState(userId);
    }

    @Override
    public void selectAccount(UUID accountId) {
        state = state.moveToAccountState(accountId);
    }

    @Override
    public void selectAccountCreator() {
        state = state.moveToAccountCreatorState();
    }

    @Override
    public Optional<Bank> tryGetBank() {
        if (state instanceof CurrentBank currentBankState)
            return Optional.of(currentBankState.getBank());
        return Optional.empty();
    }

    @Override
    public Optional<UUID> tryGetUserId() {
        if (state instanceof CurrentUser currentUserState)
            return Optional.of(currentUserState.getUserId());
        return Optional.empty();
    }

    @Override
    public Optional<UUID> tryGetAccountId() {
        if (state instanceof CurrentAccount currentAccountState)
            return Optional.of(currentAccountState.getAccountId());
        return Optional.empty();
    }
}
