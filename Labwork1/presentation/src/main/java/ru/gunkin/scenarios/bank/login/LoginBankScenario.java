package ru.gunkin.scenarios.bank.login;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.bank.BankService;
import ru.gunkin.contracts.bank.ResultBankOperation;

import java.util.Scanner;
import java.util.UUID;

public class LoginBankScenario implements Scenario {
    private final BankService bankService;

    public LoginBankScenario(BankService bankService) {
        this.bankService = bankService;
    }
    @Override
    public String getName() {
        return "Bank";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter bank id: ");
            UUID id = UUID.fromString(scanner.nextLine());

            ResultBankOperation resultBankOperation = bankService.login(id);
            System.out.println(resultBankOperation);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
