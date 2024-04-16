package ru.gunkin.scenarios.accountcreater.selectcreator;

import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.core.current.state.UserState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class SelectCreatorProvider implements ScenarioProvider {
    private final CurrentService currentService;

    public SelectCreatorProvider(CurrentService currentService) {
        this.currentService = currentService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof UserState)
            return new SelectCreatorScenario(currentService);
        return null;
    }
}
