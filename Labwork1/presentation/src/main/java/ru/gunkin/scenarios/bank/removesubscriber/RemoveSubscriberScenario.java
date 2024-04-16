package ru.gunkin.scenarios.bank.removesubscriber;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.bank.BankService;

import java.util.Scanner;
import java.util.UUID;

public class RemoveSubscriberScenario implements Scenario {
    private final BankService bankService;

    public RemoveSubscriberScenario(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public String getName() {
        return "Remove subscriber";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter user id:");
            UUID userId = UUID.fromString(scanner.nextLine());

            var result = bankService.removeSubscriber(userId);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }
}
