package ru.gunkin.contracts.builder.banks;

import ru.gunkin.contracts.operation.Operation;

import java.util.HashMap;
import java.util.UUID;

public interface AddOperations {
    BankBuilder addOperations(HashMap<UUID, Operation> operations);
}
