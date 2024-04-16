package ru.gunkin.scenarios.bank.login;

import ru.gunkin.contracts.bank.BankService;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.core.current.state.InitialState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class LoginBankProvider implements ScenarioProvider {
    private final CurrentService currentService;
    private final BankService bankService;

    public LoginBankProvider(CurrentService currentService, BankService bankService) {
        this.currentService = currentService;
        this.bankService = bankService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof InitialState)
            return new LoginBankScenario(bankService);
        return null;
    }
}
