package ru.gunkin.cleaners;

public class Cleaner {
    public static void clear() {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}