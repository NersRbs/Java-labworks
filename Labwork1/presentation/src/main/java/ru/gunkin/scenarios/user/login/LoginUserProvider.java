package ru.gunkin.scenarios.user.login;

import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.user.UserService;
import ru.gunkin.core.current.state.BankState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class LoginUserProvider implements ScenarioProvider {
    private final CurrentService currentService;

    private final UserService userService;

    public LoginUserProvider(CurrentService currentService, UserService userService) {
        this.currentService = currentService;
        this.userService = userService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof BankState) {
            return new LoginUserScenario(userService);
        }
        return null;
    }
}
