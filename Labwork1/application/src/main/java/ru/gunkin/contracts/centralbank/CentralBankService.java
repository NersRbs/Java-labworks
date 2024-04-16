package ru.gunkin.contracts.centralbank;

import java.util.UUID;


public interface CentralBankService {
    void login();

    ResultCentralBankOperation createBank(String name, double interestRate, double commission, double creditLimit);

    ResultCentralBankOperation deleteBank(UUID id);

    ResultCentralBankOperation transfer(UUID senderId, UUID receiverId, UUID senderBankId, UUID receiverBankId, double amount);

    ResultCentralBankOperation deleteAndUndoOperation(UUID id);

    String[] getBanksInformation();

    String[] getOperationsInformation();
}
