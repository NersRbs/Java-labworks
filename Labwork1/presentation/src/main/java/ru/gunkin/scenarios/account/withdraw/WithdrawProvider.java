package ru.gunkin.scenarios.account.withdraw;

import ru.gunkin.contracts.account.AccountService;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.core.current.state.AccountState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class WithdrawProvider implements ScenarioProvider {
    final CurrentService currentService;
    final AccountService accountService;

    public WithdrawProvider(CurrentService currentService, AccountService accountService) {
        this.currentService = currentService;
        this.accountService = accountService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof AccountState) {
            return new WithdrawScenario(accountService);
        }
        return null;
    }
}
