package ru.gunkin.scenarios.account.login;

import ru.gunkin.contracts.account.AccountService;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.core.current.state.UserState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class LoginAccountProvider implements ScenarioProvider {
    private final CurrentService currentService;
    private final AccountService accountService;

    public LoginAccountProvider(CurrentService currentService, AccountService accountService) {
        this.currentService = currentService;
        this.accountService = accountService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof UserState) {
            return new LoginAccountScenario(accountService);
        }
        return null;
    }
}
