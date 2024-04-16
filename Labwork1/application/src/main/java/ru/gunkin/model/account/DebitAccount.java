package ru.gunkin.model.account;

import ru.gunkin.contracts.account.Account;
import ru.gunkin.contracts.account.ResultAccountOperation;

import java.util.UUID;

public class DebitAccount implements Account {
    private final UUID id;
    private final UUID userId;
    private Double balance;

    public DebitAccount(UUID id, UUID userId, Double balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public UUID getUserId() {
        return userId;
    }

    @Override
    public Double getBalance() {
        return balance;
    }

    @Override
    public ResultAccountOperation topUp(Double amount) {
        if (amount <= 0) {
            return new ResultAccountOperation.AmountMustBePositive();
        }

        balance += amount;
        return new ResultAccountOperation.Success();
    }

    @Override
    public ResultAccountOperation withdraw(Double amount) {
        if (amount <= 0) {
            return new ResultAccountOperation.AmountMustBePositive();
        }
        if (balance < amount) {
            return new ResultAccountOperation.InsufficientFunds();
        }

        balance -= amount;
        return new ResultAccountOperation.Success();
    }

    @Override
    public String toString() {
        return "Id: " + id + ", UserId: " + userId + ", Balance: " + balance;
    }
}
