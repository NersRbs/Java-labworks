package ru.gunkin.scenarios.user.showaccounts;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.user.UserService;

import java.util.List;
import java.util.Scanner;

public class ShowAccountsScenario implements Scenario {
    private final UserService userService;

    public ShowAccountsScenario(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String getName() {
        return "Show accounts";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);

        String[] accounts = userService.getAccountsInformation();
        for (String account : accounts) {
            System.out.println(account);
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
