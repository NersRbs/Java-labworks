package ru.gunkin.scenarios.bank.adminlogin;

import ru.gunkin.contracts.bank.BankService;
import ru.gunkin.scenarios.Scenario;

public class LoginAdminScenario implements Scenario {
    private final BankService bankService;

    public LoginAdminScenario(BankService bankService) {
        this.bankService = bankService;
    }
    @Override
    public String getName() {
        return "Admin";
    }

    @Override
    public void run() {
        bankService.loginAdmin();
    }
}
