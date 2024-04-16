package ru.gunkin.scenarios.accountcreater.createdeposit;

import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.user.ResultUserOperation;
import ru.gunkin.contracts.user.UserService;
import ru.gunkin.model.account.DepositAccount;
import ru.gunkin.model.Bank;
import ru.gunkin.scenarios.Scenario;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class CreateDepositScenario implements Scenario {
    private final CurrentService currentService;
    private final UserService userService;

    public CreateDepositScenario(CurrentService currentService, UserService userService) {
        this.currentService = currentService;
        this.userService = userService;
    }
    @Override
    public String getName() {
        return "Create a deposit";
    }

    @Override
    public void run() {
        Optional<Bank> bank = currentService.tryGetBank();
        ResultUserOperation result = userService.createDepositAccount(0, LocalDate.now().plusMonths(1));
        System.out.println(result);

        System.out.println("Press \"Enter\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
