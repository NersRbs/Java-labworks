package ru.gunkin.scenarios.accountcreater.createcredit;

import ru.gunkin.contracts.current.CurrentService;
import ru.gunkin.contracts.user.UserService;
import ru.gunkin.core.current.state.AccountCreatorState;
import ru.gunkin.scenarios.Scenario;
import ru.gunkin.scenarios.ScenarioProvider;

public class CreateCreditProvider implements ScenarioProvider {
    private final CurrentService currentService;
    private final UserService userService;

    public CreateCreditProvider(CurrentService currentService, UserService userService) {
        this.currentService = currentService;
        this.userService = userService;
    }
    @Override
    public Scenario tryGetScenario() {
        if (currentService.getState() instanceof AccountCreatorState)
            return new CreateCreditScenario(currentService, userService);
        return null;
    }
}
