package ru.gunkin.scenarios.bank.deleteuser;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.bank.BankService;
import ru.gunkin.contracts.bank.ResultBankOperation;

import java.util.Scanner;
import java.util.UUID;

public class DeleteUserScenario implements Scenario {
    final private BankService bankService;

    public DeleteUserScenario(BankService bankService) {
        this.bankService = bankService;
    }
    @Override
    public String getName() {
        return "Delete a user";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter user id: ");
            UUID id = UUID.fromString(scanner.nextLine());

            ResultBankOperation result = bankService.deleteUser(id);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
