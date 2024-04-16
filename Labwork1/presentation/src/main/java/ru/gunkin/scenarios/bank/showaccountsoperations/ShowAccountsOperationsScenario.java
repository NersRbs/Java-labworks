package ru.gunkin.scenarios.bank.showaccountsoperations;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.bank.BankService;

import java.util.Scanner;

public class ShowAccountsOperationsScenario implements Scenario {
    private final BankService bankService;

    public ShowAccountsOperationsScenario(BankService bankService) {
        this.bankService = bankService;
    }
    @Override
    public String getName() {
        return "Show operations";
    }

    @Override
    public void run() {
        Cleaner.clear();
        var operationsInformation = bankService.getOperationsInformation();
        for (var operation : operationsInformation) {
            System.out.println(operation);
        }

        System.out.println("Press any key to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
