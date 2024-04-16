package ru.gunkin.scenarios.user.shownotification;

import ru.gunkin.scenarios.Scenario;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.contracts.user.UserService;

public class ShowNotificationScenario implements Scenario {
    private final UserService userService;

    public ShowNotificationScenario(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String getName() {
        return "Show notifications";
    }

    @Override
    public void run() {
        Cleaner.clear();
        var notifications = userService.getNotifications();

        for (var notification : notifications) {
            System.out.println(notification + "\n");
        }

        System.out.println("Press any key to continue...");
        var scanner = new java.util.Scanner(System.in);
        scanner.nextLine();
    }
}
