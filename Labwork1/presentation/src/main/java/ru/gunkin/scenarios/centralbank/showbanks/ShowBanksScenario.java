package ru.gunkin.scenarios.centralbank.showbanks;

import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.centralbank.CentralBankService;
import ru.gunkin.scenarios.Scenario;

import java.util.List;
import java.util.Scanner;

public class ShowBanksScenario implements Scenario {
    private final CentralBankService centralBankService;

    public ShowBanksScenario(CentralBankService centralBankService) {
        this.centralBankService = centralBankService;
    }

    @Override
    public String getName() {
        return "Show a banks";
    }

    @Override
    public void run() {
        Cleaner.clear();
        String[] banks = centralBankService.getBanksInformation();
        for (String bank : banks) {
            System.out.println(bank);
        }
        System.out.println("Press \"Enter\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
