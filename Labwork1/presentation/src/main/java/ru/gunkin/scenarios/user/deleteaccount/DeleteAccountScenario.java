package ru.gunkin.scenarios.user.deleteaccount;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.user.ResultUserOperation;
import ru.gunkin.contracts.user.UserService;

import java.util.Scanner;
import java.util.UUID;

public class DeleteAccountScenario implements Scenario {
    private final UserService userService;

    public DeleteAccountScenario(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String getName() {
        return "Delete a account";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter account id: ");
            UUID id = UUID.fromString(scanner.nextLine());
            ResultUserOperation result = userService.deleteAccount(id);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
