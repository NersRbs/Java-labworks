package ru.gunkin.scenarios.accountcreater.selectcreator;

import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.scenarios.Scenario;

public class SelectCreatorScenario implements Scenario {
    private final CurrentService currentService;

    public SelectCreatorScenario(CurrentService currentService) {
        this.currentService = currentService;
    }
    @Override
    public String getName() {
        return "Create a account";
    }

    @Override
    public void run() {
        currentService.selectAccountCreator();
    }
}
