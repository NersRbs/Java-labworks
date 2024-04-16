package ru.gunkin.scenarios.bank.adminlogin;

import ru.gunkin.contracts.bank.BankService;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.core.current.state.BankState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class LoginAdminProvider implements ScenarioProvider {
    private final CurrentService currentService;
    private final BankService bankService;

    public LoginAdminProvider(CurrentService currentService, BankService bankService) {
        this.currentService = currentService;
        this.bankService = bankService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof BankState)
            return new LoginAdminScenario(bankService);
        return null;
    }
}
