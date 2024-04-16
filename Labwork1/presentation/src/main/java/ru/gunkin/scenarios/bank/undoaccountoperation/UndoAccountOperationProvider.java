package ru.gunkin.scenarios.bank.undoaccountoperation;

import ru.gunkin.contracts.bank.BankService;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.core.current.state.AdminState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class UndoAccountOperationProvider implements ScenarioProvider {
    private final CurrentService currentService;
    private final BankService bankService;

    public UndoAccountOperationProvider(CurrentService currentService, BankService bankService) {
        this.currentService = currentService;
        this.bankService = bankService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof AdminState)
            return new UndoAccountOperationScenario(bankService);
        return null;
    }
}
