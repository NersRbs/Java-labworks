package ru.gunkin.core;

import ru.gunkin.abstraction.BanksRepository;
import ru.gunkin.contracts.account.Account;
import ru.gunkin.contracts.bank.BankService;
import ru.gunkin.contracts.bank.ResultBankOperation;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.operation.Operation;
import ru.gunkin.contracts.operation.ResultOperation;
import ru.gunkin.model.Bank;
import ru.gunkin.model.Notification;
import ru.gunkin.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

public class BankServiceImpl implements BankService {

    private final CurrentService currentService;
    private final BanksRepository banksRepository;

    public BankServiceImpl(CurrentService currentService, BanksRepository banksRepository) {
        this.currentService = currentService;
        this.banksRepository = banksRepository;
    }

    @Override
    public ResultBankOperation login(UUID id) {
        Optional<Bank> bank = banksRepository.getBank(id);
        if (bank.isEmpty()) {
            return new ResultBankOperation.CannotBeLogin("Bank with this id does not exist");
        }

        currentService.selectBank(bank.get());
        return new ResultBankOperation.Success();
    }

    @Override
    public void loginAdmin() {
        currentService.selectAdmin();
    }

    @Override
    public ResultBankOperation createUser(String firstName, String lastName, Optional<String> address, Optional<Integer> passportNumber) {
        Optional<Bank> bank = currentService.tryGetBank();
        if (bank.isEmpty()) {
            return new ResultBankOperation.CannotBeCreate("You are not in bank");
        }

        if (firstName.isEmpty())
            return new ResultBankOperation.CannotBeCreate("First name cannot be empty");

        if (lastName.isEmpty())
            return new ResultBankOperation.CannotBeCreate("Last name cannot be empty");


        UUID id;
        do {
            id = UUID.randomUUID();
        } while (bank.get().users().containsKey(id));

        var user = User.builder()
                .addId(id)
                .addFirstName(firstName)
                .addLastName(lastName)
                .addNotifications(new ArrayList<>())
                .addAddress(address)
                .addPassportNumber(passportNumber)
                .build();
        bank.get().users().put(id, user);
        return new ResultBankOperation.Success();
    }

    @Override
    public ResultBankOperation deleteUser(UUID id) {
        Optional<Bank> bank = currentService.tryGetBank();
        if (bank.isEmpty()) {
            return new ResultBankOperation.CannotBeDeleted("You are not in bank");
        }

        if (!bank.get().users().containsKey(id)) {
            return new ResultBankOperation.CannotBeDeleted("User does not exist");
        }

        bank.get().subscribers().remove(id);

        List<UUID> accountsToDelete = new ArrayList<>();
        for (Map.Entry<UUID, Account> entry : bank.get().accounts().entrySet()) {
            if (entry.getValue().getUserId().equals(id)) {
                accountsToDelete.add(entry.getKey());
            }
        }
        for (UUID accountId : accountsToDelete) {
            bank.get().accounts().remove(accountId);
        }

        bank.get().users().remove(id);
        return new ResultBankOperation.Success();
    }

    @Override
    public ResultBankOperation deleteAndUndoOperation(UUID id) {
        Optional<Bank> bank = currentService.tryGetBank();
        if (bank.isEmpty()) {
            return new ResultBankOperation.CannotBeDeleted("You are not in bank");
        }

        if (!bank.get().operations().containsKey(id)) {
            return new ResultBankOperation.CannotBeDeleted("Operation does not exist");
        }

        Operation operation = bank.get().operations().get(id);
        var result = operation.undoOperation();
        if (result instanceof ResultOperation.Failure failure) {
            return new ResultBankOperation.CannotBeDeleted(failure.toString());
        }

        bank.get().operations().remove(id);
        return new ResultBankOperation.Success();
    }

    @Override
    public void notify(String header, String message) {
        Optional<Bank> bank = currentService.tryGetBank();
        if (bank.isEmpty()) {
            return;
        }

        Notification notification = new Notification(header, message, LocalDate.now());
        for (User subscriber : bank.get().subscribers().values()) {
            subscriber.notifications().add(notification);
        }
    }

    @Override
    public ResultBankOperation addSubscriber(UUID id) {
        Optional<Bank> bank = currentService.tryGetBank();
        if (bank.isEmpty()) {
            return new ResultBankOperation.CannotBeCreate("You are not in bank");
        }
        if (!bank.get().users().containsKey(id))
            return new ResultBankOperation.CannotBeCreate("User with this id does not exist");

        User user = bank.get().users().get(id);

        if (bank.get().subscribers().containsKey(user.id())) {
            return new ResultBankOperation.CannotBeCreate("User with this id already exists");
        }

        bank.get().subscribers().put(user.id(), user);
        return new ResultBankOperation.Success();
    }

    @Override
    public ResultBankOperation removeSubscriber(UUID id) {
        Optional<Bank> bank = currentService.tryGetBank();
        if (bank.isEmpty()) {
            return new ResultBankOperation.CannotBeDeleted("You are not in bank");
        }

        if (!bank.get().users().containsKey(id)) {
            return new ResultBankOperation.CannotBeDeleted("User does not exist");
        }

        User user = bank.get().users().get(id);

        if (!bank.get().subscribers().containsKey(user.id())) {
            return new ResultBankOperation.CannotBeDeleted("User does not exist");
        }

        bank.get().subscribers().remove(user.id());
        return new ResultBankOperation.Success();
    }

    @Override
    public String[] getUsersInformation() {
        Optional<Bank> bank = currentService.tryGetBank();
        if (bank.isEmpty()) {
            return new String[0];
        }

        List<String> result = new ArrayList<>();
        for (User user : bank.get().users().values()) {
            result.add(user.toString());
        }
        return result.toArray(new String[0]);
    }

    @Override
    public String[] getOperationsInformation() {
        Optional<Bank> bank = currentService.tryGetBank();
        if (bank.isEmpty()) {
            return new String[0];
        }

        List<String> result = new ArrayList<>();
        for (Operation operation : bank.get().operations().values()) {
            result.add(operation.toString());
        }
        return result.toArray(new String[0]);
    }

}
