package ru.gunkin.scenarios.centralbank.undocentralbankoperation;

import ru.gunkin.contracts.centralbank.CentralBankService;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.core.current.state.CentralBankState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class UndoCentralBankOperationProvider implements ScenarioProvider {
    private final CurrentService currentService;
    private final CentralBankService centralBankService;

    public UndoCentralBankOperationProvider(CurrentService currentService, CentralBankService centralBankService) {
        this.currentService = currentService;
        this.centralBankService = centralBankService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof CentralBankState)
            return new UndoCentralBankOperationScenario(centralBankService);
        return null;
    }
}
