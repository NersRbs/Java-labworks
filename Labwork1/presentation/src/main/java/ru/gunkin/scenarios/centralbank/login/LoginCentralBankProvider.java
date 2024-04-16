package ru.gunkin.scenarios.centralbank.login;

import ru.gunkin.contracts.centralbank.CentralBankService;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.core.current.state.InitialState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class LoginCentralBankProvider implements ScenarioProvider {
    private final CentralBankService centralBankService;
    private final CurrentService currentService;


    public LoginCentralBankProvider(CentralBankService centralBankService, CurrentService currentService) {
        this.centralBankService = centralBankService;
        this.currentService = currentService;
    }

    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof InitialState)
            return new LoginCentralBankScenario(centralBankService);
        return null;
    }
}
