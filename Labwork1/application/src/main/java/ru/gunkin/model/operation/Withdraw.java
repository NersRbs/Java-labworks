package ru.gunkin.model.operation;

import ru.gunkin.contracts.account.Account;
import ru.gunkin.contracts.account.ResultAccountOperation;
import ru.gunkin.contracts.operation.Operation;
import ru.gunkin.contracts.operation.ResultOperation;

import java.util.HashMap;
import java.util.UUID;

public class Withdraw implements Operation {
    private final UUID id;
    private final UUID accountId;
    private final HashMap<UUID, Account> accountsRepository;
    private final double amount;

    public Withdraw(UUID id, UUID accountId, HashMap<UUID, Account> accountsRepository, double amount) {
        this.id = id;
        this.accountId = accountId;
        this.accountsRepository = accountsRepository;
        this.amount = amount;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public ResultOperation doOperation() {
        if (!accountsRepository.containsKey(accountId)) {
            return new ResultOperation.Failure("Account with id " + accountId + " does not exist");
        }
        if (amount <= 0) {
            return new ResultOperation.Failure("Amount must be greater than 0");
        }

        var account = accountsRepository.get(accountId);
        var result = account.withdraw(amount);
        if (result instanceof ResultAccountOperation.Failure failure) {
            return new ResultOperation.Failure("Failed to withdraw from account with id " + accountId + ": " + failure);
        }
        return new ResultOperation.Success();
    }

    @Override
    public ResultOperation undoOperation() {
        if (!accountsRepository.containsKey(accountId)) {
            return new ResultOperation.Failure("Account with id " + accountId + " does not exist");
        }

        var account = accountsRepository.get(accountId);
        var result = account.topUp(amount);
        if (result instanceof ResultAccountOperation.Failure failure) {
            return new ResultOperation.Failure("Failed to top up account with id " + accountId + ": " + failure);
        }
        return new ResultOperation.Success();
    }

    @Override
    public String toString() {
        return "Id: " + id + "Account id: " + accountId + ", Amount: " + amount + ", Type: Withdraw";
    }
}
