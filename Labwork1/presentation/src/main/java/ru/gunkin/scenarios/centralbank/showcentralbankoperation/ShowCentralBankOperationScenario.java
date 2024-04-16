package ru.gunkin.scenarios.centralbank.showcentralbankoperation;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.centralbank.CentralBankService;

import java.util.Scanner;

public class ShowCentralBankOperationScenario implements Scenario {
    private final CentralBankService centralBankService;

    public ShowCentralBankOperationScenario(CentralBankService centralBankService) {
        this.centralBankService = centralBankService;
    }
    @Override
    public String getName() {
        return "Show operation";
    }

    @Override
    public void run() {
        Cleaner.clear();
        var operationsInformation = centralBankService.getOperationsInformation();
        for (var operation : operationsInformation) {
            System.out.println(operation);
        }

        System.out.println("Press any key to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
