package ru.gunkin.scenarios.accountcreater.createdebit;

import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.user.ResultUserOperation;
import ru.gunkin.contracts.user.UserService;
import ru.gunkin.model.account.DebitAccount;
import ru.gunkin.model.Bank;
import ru.gunkin.scenarios.Scenario;

import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class CreateDebitScenario implements Scenario {
    private final CurrentService currentService;
    private final UserService userService;

    public CreateDebitScenario(CurrentService currentService, UserService userService) {
        this.currentService = currentService;
        this.userService = userService;
    }
    @Override
    public String getName() {
        return "Create a debit account";
    }

    @Override
    public void run() {
        ResultUserOperation result = userService.createDebitAccount(0);
        System.out.println(result);

        System.out.println("Press \"Enter\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
