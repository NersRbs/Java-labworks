package ru.gunkin.scenarios.user.showaccounts;

import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.user.UserService;
import ru.gunkin.core.current.state.UserState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class ShowAccountsProvider implements ScenarioProvider {
    private final CurrentService currentService;
    private final UserService userService;

    public ShowAccountsProvider(CurrentService currentService, UserService userService) {
        this.currentService = currentService;
        this.userService = userService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof UserState) {
            return new ShowAccountsScenario(userService);
        }
        return null;
    }
}
