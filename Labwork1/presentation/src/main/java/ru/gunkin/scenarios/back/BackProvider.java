package ru.gunkin.scenarios.back;

import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.core.current.state.InitialState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class BackProvider implements ScenarioProvider {
    private final CurrentService currentService;

    public BackProvider(CurrentService currentService) {
        this.currentService = currentService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (!(currentService.getState() instanceof InitialState))
            return new BackScenario(currentService);
        return null;
    }
}
