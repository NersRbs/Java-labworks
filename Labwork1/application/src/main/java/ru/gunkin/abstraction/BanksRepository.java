package ru.gunkin.abstraction;

import ru.gunkin.model.Bank;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BanksRepository {
    Optional<Bank> getBank(UUID id);
    boolean bankExists(UUID id);
    void addBank(Bank bank);
    void deleteBank(UUID id);

    List<Bank> getBanks();
}
