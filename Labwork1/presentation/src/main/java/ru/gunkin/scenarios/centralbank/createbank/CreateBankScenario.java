package ru.gunkin.scenarios.centralbank.createbank;

import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.centralbank.CentralBankService;
import ru.gunkin.contracts.centralbank.ResultCentralBankOperation;
import ru.gunkin.scenarios.Scenario;

import java.util.Scanner;

public class CreateBankScenario implements Scenario {
    private final CentralBankService centralBankService;


    public CreateBankScenario(CentralBankService centralBankService) {
        this.centralBankService = centralBankService;
    }
    @Override
    public String getName() {
        return "Create a bank";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter bank name: ");
            String name = scanner.nextLine();

            System.out.println("Enter interest rate: ");
            double interestRate = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter commission: ");
            double commission = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter credit limit: ");
            double creditLimit = scanner.nextInt();
            scanner.nextLine();

            ResultCentralBankOperation resultCentralBankOperation = centralBankService.createBank(name, interestRate, commission, creditLimit);
            System.out.println(resultCentralBankOperation);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
