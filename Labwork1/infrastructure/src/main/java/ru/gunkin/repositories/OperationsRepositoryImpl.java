package ru.gunkin.repositories;

import ru.gunkin.abstraction.OperationsRepository;
import ru.gunkin.contracts.operation.Operation;

import java.util.*;

public class OperationsRepositoryImpl implements OperationsRepository {
    Map<UUID, Operation> operations = new HashMap<>();
    @Override
    public Operation getOperation(UUID id) {
        if (operations.containsKey(id)) {
            return operations.get(id);
        }
        return null;
    }

    @Override
    public boolean operationExists(UUID id) {
        return operations.containsKey(id);
    }

    @Override
    public void addOperation(Operation operation) {
        UUID id;
        do {
            id = UUID.randomUUID();
        } while (operations.containsKey(id));

        operations.put(id, operation);
    }

    @Override
    public void deleteOperation(UUID id) {
        operations.remove(id);
    }

    @Override
    public List<Operation> getOperations() {
        return new ArrayList<>(operations.values());
    }
}
