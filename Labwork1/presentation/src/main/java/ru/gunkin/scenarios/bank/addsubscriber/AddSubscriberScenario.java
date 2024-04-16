package ru.gunkin.scenarios.bank.addsubscriber;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.bank.BankService;

import java.util.Scanner;
import java.util.UUID;

public class AddSubscriberScenario implements Scenario {
    private final BankService bankService;

    public AddSubscriberScenario(BankService bankService) {
        this.bankService = bankService;
    }
    @Override
    public String getName() {
        return "Add subscriber";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter user id:");
            UUID userId = UUID.fromString(scanner.nextLine());

            var result = bankService.addSubscriber(userId);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }
}
