package ru.gunkin.scenarios.bank.showaccountsoperations;

import ru.gunkin.contracts.bank.BankService;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.core.current.state.AdminState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class ShowAccountsOperationsProvider implements ScenarioProvider {
    private final CurrentService currentService;
    private final BankService bankService;

    public ShowAccountsOperationsProvider(CurrentService currentService, BankService bankService) {
        this.currentService = currentService;
        this.bankService = bankService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof AdminState)
            return new ShowAccountsOperationsScenario(bankService);
        return null;
    }
}
