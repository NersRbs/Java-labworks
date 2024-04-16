package ru.gunkin.scenarios.account.withdraw;

import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.account.AccountService;
import ru.gunkin.contracts.account.ResultAccountOperation;
import ru.gunkin.scenarios.Scenario;

import java.util.Scanner;

public class WithdrawScenario implements Scenario {
    private final AccountService service;

    public WithdrawScenario(AccountService accountService) {
        service = accountService;
    }
    @Override
    public String getName() {
        return "Withdraw";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            ResultAccountOperation result = service.withdraw(amount);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid amount");
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
