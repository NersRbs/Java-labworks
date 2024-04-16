package ru.gunkin.scenarios.user.login;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.user.ResultUserOperation;
import ru.gunkin.contracts.user.UserService;

import java.util.Scanner;
import java.util.UUID;

public class LoginUserScenario implements Scenario {
    private final UserService userService;

    public LoginUserScenario(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String getName() {
        return "User";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter your id: ");
            UUID id = UUID.fromString(scanner.nextLine());
            ResultUserOperation result = userService.login(id);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
