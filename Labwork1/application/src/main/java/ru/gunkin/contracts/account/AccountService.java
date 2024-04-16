package ru.gunkin.contracts.account;

import java.util.UUID;

public interface AccountService {
    ResultAccountOperation login(UUID id);

    ResultAccountOperation topUp(double amount);

    ResultAccountOperation withdraw(double amount);

    ResultAccountOperation transfer(UUID toId, double amount);

}
