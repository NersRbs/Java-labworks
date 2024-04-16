package ru.gunkin.scenarios.centralbank.showcentralbankoperation;

import ru.gunkin.contracts.centralbank.CentralBankService;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.core.current.state.CentralBankState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class ShowCentralBankOperationProvider implements ScenarioProvider {
    private final CurrentService currentService;
    private final CentralBankService centralBankService;

    public ShowCentralBankOperationProvider(CurrentService currentService, CentralBankService centralBankService) {
        this.currentService = currentService;
        this.centralBankService = centralBankService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof CentralBankState)
            return new ShowCentralBankOperationScenario(centralBankService);
        return null;
    }
}
