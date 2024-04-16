package ru.gunkin.scenarios.account.transfer;

import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.account.AccountService;
import ru.gunkin.contracts.account.ResultAccountOperation;
import ru.gunkin.scenarios.Scenario;

import java.util.Scanner;
import java.util.UUID;

public class TransferAccountScenario implements Scenario {
    private final AccountService accountService;

    public TransferAccountScenario(AccountService accountService) {
        this.accountService = accountService;
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
            System.out.println("Enter account id: ");
            UUID id = UUID.fromString(scanner.nextLine());

            System.out.println("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            ResultAccountOperation result = accountService.transfer(id, amount);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
