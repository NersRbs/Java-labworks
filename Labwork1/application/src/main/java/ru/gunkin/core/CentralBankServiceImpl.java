package ru.gunkin.core;

import ru.gunkin.abstraction.BanksRepository;
import ru.gunkin.abstraction.OperationsRepository;
import ru.gunkin.contracts.centralbank.CentralBankService;
import ru.gunkin.contracts.centralbank.ResultCentralBankOperation;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.operation.Operation;
import ru.gunkin.contracts.operation.ResultOperation;
import ru.gunkin.model.Bank;
import ru.gunkin.model.operation.Transfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.List;


public class CentralBankServiceImpl implements CentralBankService {

    private final CurrentService currentService;
    private final BanksRepository banksRepository;
    private final OperationsRepository operationsRepository;


    public CentralBankServiceImpl(CurrentService currentService, BanksRepository banksRepository, OperationsRepository operationsRepository) {
        this.currentService = currentService;
        this.banksRepository = banksRepository;
        this.operationsRepository = operationsRepository;
    }

    @Override
    public void login() {
        currentService.selectCentralBank();
    }

    @Override
    public ResultCentralBankOperation createBank(String name, double interestRate, double commission, double creditLimit) {
        if (name.isEmpty()) {
            return new ResultCentralBankOperation.CannotBeCreate("Name cannot be empty");
        }

        if (interestRate < 0 || interestRate > 100) {
            return new ResultCentralBankOperation.CannotBeCreate("Interest rate must be between 0 and 100");
        }

        if (commission < 0) {
            return new ResultCentralBankOperation.CannotBeCreate("Commission cannot be negative");
        }

        if (creditLimit < 0) {
            return new ResultCentralBankOperation.CannotBeCreate("Credit limit cannot be negative");
        }

        UUID id;
        do {
            id = UUID.randomUUID();
        } while (banksRepository.bankExists(id));

        var bank = Bank.builder()
                        .id(id)
                        .addName(name)
                        .addInterestRate(interestRate)
                        .addCommission(commission)
                        .addCreditLimit(creditLimit)
                        .addSubscribers(new HashMap<>())
                        .addUsers(new HashMap<>())
                        .addAccounts(new HashMap<>())
                        .addOperations(new HashMap<>())
                        .build();

        banksRepository.addBank(bank);
        return new ResultCentralBankOperation.Success();
    }

    @Override
    public ResultCentralBankOperation deleteBank(UUID id) {
        if (!banksRepository.bankExists(id)) {
            return new ResultCentralBankOperation.CannotBeDeleted("Bank does not exist");
        }
        banksRepository.deleteBank(id);
        return new ResultCentralBankOperation.Success();
    }

    @Override
    public ResultCentralBankOperation transfer(UUID senderId, UUID receiverId, UUID senderBankId, UUID receiverBankId, double amount) {
        Optional<Bank> senderBank = banksRepository.getBank(senderBankId);
        Optional<Bank> receiverBank = banksRepository.getBank(receiverBankId);

        if (senderBank.isEmpty()) {
            return new ResultCentralBankOperation.CannotBeTransfer("Sender bank does not exist");
        }

        if (receiverBank.isEmpty()) {
            return new ResultCentralBankOperation.CannotBeTransfer("Receiver bank does not exist");
        }

        var senderRepository = senderBank.get().accounts();
        var receiverRepository = receiverBank.get().accounts();

        if (!senderRepository.containsKey(senderId)) {
            return new ResultCentralBankOperation.CannotBeTransfer("Sender account does not exist");
        }

        if (!receiverRepository.containsKey(receiverId)) {
            return new ResultCentralBankOperation.CannotBeTransfer("Receiver account does not exist");
        }

        UUID id;
        do {
            id = UUID.randomUUID();
        } while (operationsRepository.operationExists(id));

        var operation = new Transfer(id, senderId, receiverId, senderRepository, receiverRepository, amount);
        var result = operation.doOperation();
        if (result instanceof ResultOperation.Failure failure) {
            return new ResultCentralBankOperation.CannotBeTransfer(failure.toString());
        }


        operationsRepository.addOperation(operation);
        return new ResultCentralBankOperation.Success();
    }

    @Override
    public ResultCentralBankOperation deleteAndUndoOperation(UUID id) {
        if (!operationsRepository.operationExists(id)) {
            return new ResultCentralBankOperation.CannotBeDeleted("Operation does not exist");
        }

        var operation = operationsRepository.getOperation(id);
        var result = operation.undoOperation();
        if (result instanceof ResultOperation.Failure failure) {
            return new ResultCentralBankOperation.CannotBeDeleted(failure.toString());
        }

        operationsRepository.deleteOperation(id);
        return new ResultCentralBankOperation.Success();
    }

    @Override
    public String[] getBanksInformation() {
        List<String> result = new ArrayList<>();
        List<Bank> banks = banksRepository.getBanks();
        for (Bank bank : banks) {
            result.add(bank.toString());
        }

        return result.toArray(new String[0]);
    }

    @Override
    public String[] getOperationsInformation() {
        List<String> result = new ArrayList<>();
        for (Operation operation : operationsRepository.getOperations()) {
            result.add(operation.toString());
        }

        return result.toArray(new String[0]);
    }
}
