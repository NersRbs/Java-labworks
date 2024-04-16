package ru.gunkin.scenarios.account.topup;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.account.AccountService;
import ru.gunkin.contracts.account.ResultAccountOperation;

import java.util.Scanner;

public class TopUpScenario implements Scenario {
    private final AccountService accountService;

    public TopUpScenario(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public String getName() {
        return "Top up";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            ResultAccountOperation result = accountService.topUp(amount);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid amount");
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
