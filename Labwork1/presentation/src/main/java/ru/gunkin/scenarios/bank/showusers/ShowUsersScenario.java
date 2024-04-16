package ru.gunkin.scenarios.bank.showusers;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.bank.BankService;

import java.util.List;
import java.util.Scanner;

public class ShowUsersScenario implements Scenario {
    private final BankService bankService;

    public ShowUsersScenario(BankService bankService) {
        this.bankService = bankService;
    }
    @Override
    public String getName() {
        return "Show users";
    }

    @Override
    public void run() {
        Cleaner.clear();
        String[] users = bankService.getUsersInformation();
        for (String user : users) {
            System.out.println(user);
        }

        System.out.println("Press \"Enter\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
