package ru.gunkin.scenarios.centralbank.login;

import ru.gunkin.contracts.centralbank.CentralBankService;
import ru.gunkin.scenarios.Scenario;

public class LoginCentralBankScenario implements Scenario {
    private final CentralBankService centralBankService;

    public LoginCentralBankScenario(CentralBankService centralBankService) {
        this.centralBankService = centralBankService;
    }

    @Override
    public String getName() {
        return "Central Bank";
    }

    @Override
    public void run() {
        centralBankService.login();
    }
}
