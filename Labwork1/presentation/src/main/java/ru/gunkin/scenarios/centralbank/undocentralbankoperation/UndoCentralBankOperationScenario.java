package ru.gunkin.scenarios.centralbank.undocentralbankoperation;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.centralbank.CentralBankService;

import java.util.Scanner;
import java.util.UUID;

public class UndoCentralBankOperationScenario implements Scenario {
    private final CentralBankService centralBankService;

    public UndoCentralBankOperationScenario(CentralBankService centralBankService) {
        this.centralBankService = centralBankService;
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

            var result = centralBankService.deleteAndUndoOperation(operationId);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
