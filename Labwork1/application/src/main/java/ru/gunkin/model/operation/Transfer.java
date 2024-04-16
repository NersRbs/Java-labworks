package ru.gunkin.model.operation;

import ru.gunkin.contracts.account.Account;
import ru.gunkin.contracts.account.ResultAccountOperation;
import ru.gunkin.contracts.operation.Operation;
import ru.gunkin.contracts.operation.ResultOperation;

import java.util.HashMap;
import java.util.UUID;

public class Transfer implements Operation {
    private final UUID id;
    private final UUID fromId;
    private final UUID toId;
    private final HashMap<UUID, Account> fromRepository;
    private final HashMap<UUID, Account> toRepository;

    double amount;

    public Transfer(UUID id, UUID fromId, UUID toId, HashMap<UUID, Account> fromRepository, HashMap<UUID, Account> toRepository, double amount) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.fromRepository = fromRepository;
        this.toRepository = toRepository;
        this.amount = amount;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public ResultOperation doOperation() {
        if (!fromRepository.containsKey(fromId)) {
            return new ResultOperation.Failure("Account with id " + fromId + " does not exist");
        }

        if (!toRepository.containsKey(toId)) {
            return new ResultOperation.Failure("Account with id " + toId + " does not exist");
        }

        if (amount <= 0) {
            return new ResultOperation.Failure("Amount must be greater than 0");
        }

        var fromAccount = fromRepository.get(fromId);
        var toAccount = toRepository.get(toId);

        var fromResult = fromAccount.withdraw(amount);
        if (fromResult instanceof ResultAccountOperation.Failure failure) {
            return new ResultOperation.Failure("Failed to withdraw from account with id " + fromId + ": " + failure);
        }

        var toResult = toAccount.topUp(amount);
        if (toResult instanceof ResultAccountOperation.Failure failure) {
            fromAccount.topUp(amount);
            return new ResultOperation.Failure("Failed to top up account with id " + toId + ": " + failure);
        }

        return new ResultOperation.Success();
    }

    @Override
    public ResultOperation undoOperation() {
        if (!fromRepository.containsKey(fromId)) {
            return new ResultOperation.Failure("Account with id " + fromId + " does not exist");
        }

        if (!toRepository.containsKey(toId)) {
            return new ResultOperation.Failure("Account with id " + toId + " does not exist");
        }

        var fromAccount = fromRepository.get(fromId);
        var toAccount = toRepository.get(toId);

        var fromResult = fromAccount.topUp(amount);
        if (fromResult instanceof ResultAccountOperation.Failure failure) {
            return new ResultOperation.Failure("Failed to top up account with id " + fromId + ": " + failure);
        }

        var toResult = toAccount.withdraw(amount);
        if (toResult instanceof ResultAccountOperation.Failure failure) {
            fromAccount.withdraw(amount);
            return new ResultOperation.Failure("Failed to withdraw from account with id " + toId + ": " + failure);
        }

        return new ResultOperation.Success();
    }

    @Override
    public String toString() {
        return "Id: " + id + ", From account id: " + fromId + ", To account id: " + toId + ", Amount: " + amount + ", Type: Transfer";
    }
}
