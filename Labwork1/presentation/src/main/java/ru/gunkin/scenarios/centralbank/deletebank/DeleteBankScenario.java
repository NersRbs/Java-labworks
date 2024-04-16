package ru.gunkin.scenarios.centralbank.deletebank;

import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.centralbank.CentralBankService;
import ru.gunkin.contracts.centralbank.ResultCentralBankOperation;
import ru.gunkin.scenarios.Scenario;

import java.util.Scanner;
import java.util.UUID;

public class DeleteBankScenario implements Scenario {
    private final CentralBankService centralBankService;

    public DeleteBankScenario(CentralBankService centralBankService) {
        this.centralBankService = centralBankService;
    }
    @Override
    public String getName() {
        return "Delete a bank";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter bank id: ");
            UUID id = UUID.fromString(scanner.nextLine());

            ResultCentralBankOperation resultCentralBankOperation = centralBankService.deleteBank(id);
            System.out.println(resultCentralBankOperation);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
