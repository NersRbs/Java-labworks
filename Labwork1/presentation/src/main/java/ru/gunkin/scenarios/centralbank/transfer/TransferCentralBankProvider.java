package ru.gunkin.scenarios.centralbank.transfer;

import ru.gunkin.contracts.centralbank.CentralBankService;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.core.current.state.CentralBankState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class TransferCentralBankProvider implements ScenarioProvider {
    private final CurrentService currentService;
    private final CentralBankService centralBankService;

    public TransferCentralBankProvider(CurrentService currentService, CentralBankService centralBankService) {
        this.currentService = currentService;
        this.centralBankService = centralBankService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof CentralBankState)
            return new TransferCentralBankScenario(centralBankService);
        return null;
    }
}
