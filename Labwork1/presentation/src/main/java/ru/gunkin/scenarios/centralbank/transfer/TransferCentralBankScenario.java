package ru.gunkin.scenarios.centralbank.transfer;

import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.centralbank.CentralBankService;
import ru.gunkin.contracts.centralbank.ResultCentralBankOperation;
import ru.gunkin.scenarios.Scenario;

import java.util.Scanner;
import java.util.UUID;

public class TransferCentralBankScenario implements Scenario {
    CentralBankService centralBankService;

    public TransferCentralBankScenario(CentralBankService centralBankService) {
        this.centralBankService = centralBankService;
    }
    @Override
    public String getName() {
        return "Transfer";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("First account id: ");
            UUID firstAccountId = UUID.fromString(scanner.nextLine());

            System.out.println("Second account id: ");
            UUID secondAccountId = UUID.fromString(scanner.nextLine());

            System.out.println("First bank id: ");
            UUID firstBankId = UUID.fromString(scanner.nextLine());

            System.out.println("Second bank id: ");
            UUID secondBankId = UUID.fromString(scanner.nextLine());

            System.out.println("Amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            ResultCentralBankOperation result = centralBankService.transfer(firstAccountId, secondAccountId, firstBankId, secondBankId, amount);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
