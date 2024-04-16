package ru.gunkin.contracts.user;

import java.time.LocalDate;
import java.util.UUID;

public interface UserService {
    ResultUserOperation login(UUID id);

    ResultUserOperation createDebitAccount(double balance);

    ResultUserOperation createDepositAccount(double balance, LocalDate dateWhenCanBeWithdrawn);

    ResultUserOperation createCreditAccount(double balance, double creditLimit);

    ResultUserOperation deleteAccount(UUID id);

    String[] getAccountsInformation();

    String[] getNotifications();

}
