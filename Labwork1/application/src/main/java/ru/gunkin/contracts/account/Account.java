package ru.gunkin.contracts.account;

import java.util.UUID;

public interface Account {
    UUID getId();
    UUID getUserId();
    Double getBalance();
    ResultAccountOperation topUp(Double amount);
    ResultAccountOperation withdraw(Double amount);
}
