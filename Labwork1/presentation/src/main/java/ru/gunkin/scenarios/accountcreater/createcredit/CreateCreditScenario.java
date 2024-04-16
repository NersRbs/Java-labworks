package ru.gunkin.scenarios.accountcreater.createcredit;

import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.user.ResultUserOperation;
import ru.gunkin.contracts.user.UserService;
import ru.gunkin.model.account.CreditAccount;
import ru.gunkin.model.Bank;
import ru.gunkin.scenarios.Scenario;

import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class CreateCreditScenario implements Scenario {
    private final CurrentService currentService;
    private final UserService userService;

    public CreateCreditScenario(CurrentService currentService, UserService userService) {
        this.currentService = currentService;
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "Create a credit account";
    }

    @Override
    public void run() {
        Optional<Bank> bank = currentService.tryGetBank();
        ResultUserOperation result = userService.createCreditAccount(0, bank.get().creditLimit());
        System.out.println(result);

        System.out.println("Press \"Enter\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
