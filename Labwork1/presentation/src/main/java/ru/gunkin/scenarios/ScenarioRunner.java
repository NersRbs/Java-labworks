package ru.gunkin.scenarios;

import ru.gunkin.selectors.Selector;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ScenarioRunner {
    private final List<ScenarioProvider> providers;

    public ScenarioRunner(List<ScenarioProvider> providers) {
        this.providers = providers;
    }

    public void run() {
        List<Scenario> scenarios = getScenarios();
        Selector<Scenario> selector = new Selector<>(scenarios);

        Optional<Scenario> selectedScenario = selector.select();
        selectedScenario.ifPresent(Scenario::run);
    }

    private List<Scenario> getScenarios() {
        return providers.stream()
                .map(ScenarioProvider::tryGetScenario)
                .filter(Objects::nonNull)
                .toList();
    }
}
