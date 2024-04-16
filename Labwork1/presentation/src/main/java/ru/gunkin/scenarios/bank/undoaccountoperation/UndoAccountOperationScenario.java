package ru.gunkin.scenarios.bank.undoaccountoperation;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.bank.BankService;

import java.util.Scanner;
import java.util.UUID;

public class UndoAccountOperationScenario implements Scenario {
    private final BankService bankService;

    public UndoAccountOperationScenario(BankService bankService) {
        this.bankService = bankService;
    }
    @Override
    public String getName() {
        return "Undo operation";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Operation id: ");
            UUID operationId = UUID.fromString(scanner.nextLine());

            var result = bankService.deleteAndUndoOperation(operationId);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
