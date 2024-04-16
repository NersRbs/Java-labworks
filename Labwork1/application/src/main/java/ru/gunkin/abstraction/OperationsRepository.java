package ru.gunkin.abstraction;

import ru.gunkin.contracts.operation.Operation;

import java.util.List;
import java.util.UUID;

public interface OperationsRepository {

    Operation getOperation(UUID id);
    boolean operationExists(UUID id);
    void addOperation(Operation operation);
    void deleteOperation(UUID id);

    List<Operation> getOperations();
}
