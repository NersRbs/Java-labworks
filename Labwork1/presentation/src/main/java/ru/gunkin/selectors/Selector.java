package ru.gunkin.selectors;

import ru.gunkin.scenarios.Scenario;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Selector<T extends Scenario> {
    private final List<T> items;

    public Selector(List<T>  items) {
        this.items = items;
    }

    public Optional<T> select() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + ". " + items.get(i).getName());
        }

        try {
            int index = scanner.nextInt();
            return Optional.of(items.get(index));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
