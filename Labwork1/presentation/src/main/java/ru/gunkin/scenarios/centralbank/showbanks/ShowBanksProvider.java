package ru.gunkin.scenarios.centralbank.showbanks;

import ru.gunkin.contracts.centralbank.CentralBankService;
import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.core.current.state.CentralBankState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class ShowBanksProvider implements ScenarioProvider {
    private final CentralBankService centralBankService;
    private final CurrentService currentService;

    public ShowBanksProvider(CentralBankService centralBankService, CurrentService currentService) {
        this.centralBankService = centralBankService;
        this.currentService = currentService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof CentralBankState)
            return new ShowBanksScenario(centralBankService);
        return null;
    }
}
