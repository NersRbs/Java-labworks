package ru.gunkin.core;

import ru.gunkin.contracts.account.Account;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.user.ResultUserOperation;
import ru.gunkin.contracts.user.UserService;
import ru.gunkin.model.Bank;
import ru.gunkin.model.User;
import ru.gunkin.model.account.CreditAccount;
import ru.gunkin.model.account.DebitAccount;
import ru.gunkin.model.account.DepositAccount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private final CurrentService currentService;

    public UserServiceImpl(CurrentService currentService) {
        this.currentService = currentService;
    }

    @Override
    public ResultUserOperation login(UUID id) {
        Optional<Bank> bank = currentService.tryGetBank();
        if (bank.isEmpty()) {
            return new ResultUserOperation.CannotBeLogin("You are not in bank");
        }

        if (!bank.get().users().containsKey(id)) {
            return new ResultUserOperation.CannotBeLogin("User with this id does not exist");
        }

        currentService.selectUser(id);
        return new ResultUserOperation.Success();
    }

    @Override
    public ResultUserOperation createDebitAccount(double balance) {
        Optional<UUID> userId = currentService.tryGetUserId();

        if (userId.isEmpty()) {
            return new ResultUserOperation.CannotBeCreate("You are not logged in");
        }

        if (balance < 0) {
            return new ResultUserOperation.CannotBeCreate("Balance must be positive");
        }

        UUID id;
        do {
            id = UUID.randomUUID();
        } while (currentService.tryGetBank().get().accounts().containsKey(id));

        DebitAccount debitAccount = new DebitAccount(id, userId.get(), balance);
        return createAccount(debitAccount);
    }

    @Override
    public ResultUserOperation createDepositAccount(double balance, LocalDate dateWhenCanBeWithdrawn) {
        Optional<UUID> userId = currentService.tryGetUserId();

        if (userId.isEmpty()) {
            return new ResultUserOperation.CannotBeCreate("You are not logged in");
        }

        if (balance < 0) {
            return new ResultUserOperation.CannotBeCreate("Balance must be positive");
        }

        if (LocalDate.now().isAfter(dateWhenCanBeWithdrawn)) {
            return new ResultUserOperation.CannotBeCreate("Date when can be withdrawn must be in the future");
        }

        UUID id;
        do {
            id = UUID.randomUUID();
        } while (currentService.tryGetBank().get().accounts().containsKey(id));

        DepositAccount depositAccount = new DepositAccount(id, userId.get(), balance, dateWhenCanBeWithdrawn);
        return createAccount(depositAccount);
    }

    @Override
    public ResultUserOperation createCreditAccount(double balance, double creditLimit) {
        Optional<UUID> userId = currentService.tryGetUserId();

        if (userId.isEmpty()) {
            return new ResultUserOperation.CannotBeCreate("You are not logged in");
        }

        if (balance < 0) {
            return new ResultUserOperation.CannotBeCreate("Balance must be positive");
        }

        if (creditLimit < 0) {
            return new ResultUserOperation.CannotBeCreate("Credit limit must be positive");
        }

        UUID id;
        do {
            id = UUID.randomUUID();
        } while (currentService.tryGetBank().get().accounts().containsKey(id));

        CreditAccount creditAccount = new CreditAccount(id, userId.get(), balance, creditLimit);
        return createAccount(creditAccount);
    }


    @Override
    public ResultUserOperation deleteAccount(UUID id) {
        Optional<Bank> bank = currentService.tryGetBank();
        Optional<UUID> userId = currentService.tryGetUserId();
        if (bank.isEmpty()) {
            return new ResultUserOperation.CannotBeDeleted("You are not in bank");
        }

        if (userId.isEmpty()) {
            return new ResultUserOperation.CannotBeDeleted("You are not logged in");
        }

        if (!bank.get().users().containsKey(userId.get())) {
            return new ResultUserOperation.CannotBeDeleted("User with this id does not exist");
        }

        if (bank.get().accounts().get(id).getUserId() != userId.get()) {
            return new ResultUserOperation.CannotBeDeleted("Account with this id does not exist");
        }

        if (!bank.get().accounts().containsKey(id))
            return new ResultUserOperation.CannotBeDeleted("Account with this id does not exist");

        bank.get().accounts().remove(id);
        return new ResultUserOperation.Success();
    }

    @Override
    public String[] getAccountsInformation() {
        Optional<Bank> bank = currentService.tryGetBank();
        Optional<UUID> userId = currentService.tryGetUserId();

        if (bank.isEmpty() || userId.isEmpty()) {
            return new String[0];
        }

        List<String> result = new ArrayList<>();
        for (var account : bank.get().accounts().values()) {
            if (!account.getUserId().equals(userId.get())) {
                continue;
            }
            result.add(account.toString());
        }

        return result.toArray(new String[0]);
    }

    @Override
    public String[] getNotifications() {
        Optional<Bank> bank = currentService.tryGetBank();
        Optional<UUID> userId = currentService.tryGetUserId();

        if (bank.isEmpty() || userId.isEmpty()) {
            return new String[0];
        }

        if (!bank.get().users().containsKey(userId.get())) {
            return new String[0];
        }

        List<String> result = new ArrayList<>();
        User user = bank.get().users().get(userId.get());

        for (var notification : user.notifications()) {
            result.add(notification.toString());
        }

        return result.toArray(new String[0]);
    }

    private ResultUserOperation createAccount(Account account) {
        Optional<Bank> bank = currentService.tryGetBank();
        if (bank.isEmpty()) {
            return new ResultUserOperation.CannotBeCreate("You are not in bank");
        }

        bank.get().accounts().put(account.getId(), account);
        return new ResultUserOperation.Success();
    }
}
