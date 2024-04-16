package ru.gunkin.model;

import ru.gunkin.contracts.account.Account;
import ru.gunkin.contracts.builder.banks.*;
import ru.gunkin.contracts.operation.Operation;

import java.util.HashMap;
import java.util.UUID;

public record Bank(UUID id, String name, Double interestRate, Double commission, Double creditLimit,
                   HashMap<UUID, User> subscribers, HashMap<UUID, User> users,
                   HashMap<UUID, Account> accounts, HashMap<UUID, Operation> operations) {

    public static AddId builder() {
        return new Builder();
    }

    private static class Builder implements AddId, AddName, AddInterestRate, AddCommission,
            AddCreditLimit, AddSubscribers, AddUsers, AddAccounts, AddOperations, BankBuilder {
        private UUID id;
        private String name;
        private Double interestRate;
        private Double commission;
        private Double creditLimit;
        private HashMap<UUID, User> subscribers;
        private HashMap<UUID, User> users;
        private HashMap<UUID, Account> accounts;
        private HashMap<UUID, Operation> operations;
        @Override
        public AddOperations addAccounts(HashMap<UUID, Account> accounts) {
            this.accounts = accounts;
            return this;
        }

        @Override
        public AddCreditLimit addCommission(Double commission) {
            this.commission = commission;
            return this;
        }

        @Override
        public AddSubscribers addCreditLimit(Double creditLimit) {
            this.creditLimit = creditLimit;
            return this;
        }

        @Override
        public AddName id(UUID id) {
            this.id = id;
            return this;
        }

        @Override
        public AddCommission addInterestRate(Double interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        @Override
        public AddInterestRate addName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public BankBuilder addOperations(HashMap<UUID, Operation> operations) {
            this.operations = operations;
            return this;
        }

        @Override
        public AddUsers addSubscribers(HashMap<UUID, User> subscribers) {
            this.subscribers = subscribers;
            return this;
        }

        @Override
        public AddAccounts addUsers(HashMap<UUID, User> users) {
            this.users = users;
            return this;
        }

        @Override
        public Bank build() {
            return new Bank(id, name, interestRate, commission,
                    creditLimit, subscribers, users,
                    accounts, operations);
        }
    }
}
