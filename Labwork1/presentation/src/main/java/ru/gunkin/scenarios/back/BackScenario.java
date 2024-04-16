package ru.gunkin.scenarios.back;

import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.scenarios.Scenario;

public class BackScenario implements Scenario {
    private final CurrentService currentService;

    public BackScenario(CurrentService currentService) {
        this.currentService = currentService;
    }
    @Override
    public String getName() {
        return "Back";
    }

    @Override
    public void run() {
        currentService.selectBack();
    }
}
