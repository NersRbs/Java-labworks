package ru.gunkin.scenarios.bank.createuser;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.bank.BankService;
import ru.gunkin.contracts.bank.ResultBankOperation;

import java.util.Optional;
import java.util.Scanner;

public class CreateUserScenario implements Scenario {
    private final BankService bankService;

    public CreateUserScenario(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public String getName() {
        return "Create user";
    }

    @Override
    public void run() {
        Cleaner.clear();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter first name: ");
            String firstName = scanner.nextLine();

            System.out.println("Enter last name: ");
            String lastName = scanner.nextLine();

            System.out.println("Enter address or press \"Enter\" to skip: ");
            String address = scanner.nextLine();

            System.out.println("Enter passport number or press \"Enter\" to skip: ");
            String passportNumber = scanner.nextLine();

            ResultBankOperation resultBankOperation = bankService.createUser(firstName, lastName,
                    address.isEmpty() ? Optional.empty() : Optional.of(address),
                    passportNumber.isEmpty() ? Optional.empty() : Optional.of(Integer.parseInt(passportNumber)));
            System.out.println(resultBankOperation);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println("Press \"Enter\" to continue...");
        scanner.nextLine();
    }
}
