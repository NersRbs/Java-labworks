package ru.gunkin.scenarios.bank.notify;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.bank.BankService;

import java.util.Scanner;

public class NotifyScenario implements Scenario {
    private final BankService bankService;

    public NotifyScenario(BankService bankService) {
        this.bankService = bankService;
    }
    @Override
    public String getName() {
        return "Notify";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter header:");
            String header = scanner.nextLine();

            System.out.println("Enter message:");
            String message = scanner.nextLine();

            bankService.notify(header, message);
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }
}
