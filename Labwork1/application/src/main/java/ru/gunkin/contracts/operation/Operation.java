package ru.gunkin.contracts.operation;

import java.util.UUID;

public interface Operation {
    UUID getId();
    ResultOperation doOperation();
    ResultOperation undoOperation();
}
