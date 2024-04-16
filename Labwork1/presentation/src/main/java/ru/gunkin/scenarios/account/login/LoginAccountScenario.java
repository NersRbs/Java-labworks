package ru.gunkin.scenarios.account.login;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.account.AccountService;
import ru.gunkin.contracts.account.ResultAccountOperation;
import ru.gunkin.contracts.current.CurrentService;

import java.util.Scanner;
import java.util.UUID;

public class LoginAccountScenario implements Scenario {
    private final AccountService accountService;

    public LoginAccountScenario(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public String getName() {
        return "Login Account";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter account id: ");
            UUID id = UUID.fromString(scanner.nextLine());

            ResultAccountOperation result = accountService.login(id);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid id");
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
