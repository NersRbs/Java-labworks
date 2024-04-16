package ru.gunkin.scenarios.account.topup;

import ru.gunkin.contracts.account.AccountService;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.core.current.state.AccountState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class TopUpProvider implements ScenarioProvider {
    private final CurrentService currentService;
    private final AccountService accountService;

    public TopUpProvider(CurrentService currentService, AccountService accountService) {
        this.currentService = currentService;
        this.accountService = accountService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof AccountState) {
            return new TopUpScenario(accountService);
        }
        return null;
    }
}
