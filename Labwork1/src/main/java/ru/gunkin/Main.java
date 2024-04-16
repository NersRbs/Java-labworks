package ru.gunkin;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gunkin.cleaners.Cleaner;
import ru.gunkin.configurations.ScenarioConfiguration;
import ru.gunkin.scenarios.ScenarioRunner;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScenarioConfiguration.class);
        ScenarioRunner scenarioRunner = context.getBean(ScenarioRunner.class);

        while (true) {
            scenarioRunner.run();
            Cleaner.clear();
        }
    }
}