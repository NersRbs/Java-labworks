package ru.gunkin.repositories;

import ru.gunkin.abstraction.BanksRepository;
import ru.gunkin.model.Bank;

import java.util.*;

public class BanksRepositoryImpl implements BanksRepository {
    private final Map<UUID, Bank> banks = new HashMap<>();

    @Override
    public Optional<Bank> getBank(UUID id) {
        if (banks.containsKey(id)) {
            return Optional.of(banks.get(id));
        }
        return Optional.empty();
    }

    @Override
    public boolean bankExists(UUID id) {
        return banks.containsKey(id);
    }

    @Override
    public void addBank(Bank bank) {
        banks.put(bank.id(), bank);
    }


    @Override
    public void deleteBank(UUID id) {
        banks.remove(id);
    }

    @Override
    public List<Bank> getBanks() {
        return new ArrayList<>(banks.values());
    }
}
