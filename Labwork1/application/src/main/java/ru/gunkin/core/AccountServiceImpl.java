package ru.gunkin.core;

import ru.gunkin.contracts.account.AccountService;
import ru.gunkin.contracts.account.ResultAccountOperation;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.operation.Operation;
import ru.gunkin.contracts.operation.ResultOperation;
import ru.gunkin.model.Bank;
import ru.gunkin.model.operation.TopUp;
import ru.gunkin.model.operation.Transfer;
import ru.gunkin.model.operation.Withdraw;

import java.util.Optional;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {
    private final CurrentService currentService;

    public AccountServiceImpl(CurrentService currentService) {
        this.currentService = currentService;
    }

    @Override
    public ResultAccountOperation login(UUID id) {
        Optional<Bank> bank = currentService.tryGetBank();
        Optional<UUID> userId = currentService.tryGetUserId();
        if (bank.isEmpty()) {
            return new ResultAccountOperation.CannotBeLogin("You are not in bank");
        }

        if (userId.isEmpty()) {
            return new ResultAccountOperation.CannotBeLogin("You are not logged in");
        }

        if (!bank.get().accounts().containsKey(id)) {
            return new ResultAccountOperation.CannotBeLogin("Account with this id does not exist");
        }

        if (!bank.get().accounts().containsKey(id) &&
                !bank.get().accounts().get(id).getUserId().equals(userId.get())) {
            return new ResultAccountOperation.CannotBeLogin("Account with this id does not belong to you");
        }

        currentService.selectAccount(id);
        return new ResultAccountOperation.Success();
    }

    @Override
    public ResultAccountOperation topUp(double amount) {
        Optional<Bank> bank = currentService.tryGetBank();
        Optional<UUID> userId = currentService.tryGetUserId();
        Optional<UUID> accountId = currentService.tryGetAccountId();
        if (bank.isEmpty()) {
            return new ResultAccountOperation.CannotBeTopUp("You are not in bank");
        }

        if (userId.isEmpty()) {
            return new ResultAccountOperation.CannotBeTopUp("You are not logged in");
        }

        if (accountId.isEmpty()) {
            return new ResultAccountOperation.CannotBeTopUp("You are not logged in");
        }

        UUID id;
        do {
            id = UUID.randomUUID();
        } while (bank.get().operations().containsKey(id));

        Operation topUp = new TopUp(id, accountId.get(), bank.get().accounts(), amount);
        var result = topUp.doOperation();
        if (result instanceof ResultOperation.Failure failure) {
            return new ResultAccountOperation.CannotBeTopUp(failure.toString());
        }

        bank.get().operations().put(topUp.getId(), topUp);
        return new ResultAccountOperation.Success();
    }

    @Override
    public ResultAccountOperation withdraw(double amount) {
        Optional<Bank> bank = currentService.tryGetBank();
        Optional<UUID> userId = currentService.tryGetUserId();
        Optional<UUID> accountId = currentService.tryGetAccountId();
        if (bank.isEmpty()) {
            return new ResultAccountOperation.CannotBeWithdraw("You are not in bank");
        }

        if (userId.isEmpty()) {
            return new ResultAccountOperation.CannotBeWithdraw("You are not logged in");
        }

        if (accountId.isEmpty()) {
            return new ResultAccountOperation.CannotBeWithdraw("You are not logged in");
        }

        UUID id;
        do {
            id = UUID.randomUUID();
        } while (bank.get().operations().containsKey(id));

        Operation withdraw = new Withdraw(id, accountId.get(), bank.get().accounts(), amount);
        var result = withdraw.doOperation();
        if (result instanceof ResultOperation.Failure failure) {
            return new ResultAccountOperation.CannotBeWithdraw(failure.toString());
        }

        bank.get().operations().put(withdraw.getId(), withdraw);
        return new ResultAccountOperation.Success();
    }

    @Override
    public ResultAccountOperation transfer(UUID toId, double amount) {
        Optional<Bank> bank = currentService.tryGetBank();
        Optional<UUID> userId = currentService.tryGetUserId();
        Optional<UUID> accountId = currentService.tryGetAccountId();
        if (bank.isEmpty()) {
            return new ResultAccountOperation.CannotBeTransfer("You are not in bank");
        }

        if (userId.isEmpty()) {
            return new ResultAccountOperation.CannotBeTransfer("You are not logged in");
        }

        if (accountId.isEmpty()) {
            return new ResultAccountOperation.CannotBeTransfer("You are not logged in");
        }

        UUID id;
        do {
            id = UUID.randomUUID();
        } while (bank.get().operations().containsKey(id));

        Operation transfer = new Transfer(id, accountId.get(), toId, bank.get().accounts(), bank.get().accounts(), amount);
        var result = transfer.doOperation();
        if (result instanceof ResultOperation.Failure failure) {
            return new ResultAccountOperation.CannotBeTransfer(failure.toString());
        }

        bank.get().operations().put(transfer.getId(), transfer);
        return new ResultAccountOperation.Success();
    }
}
