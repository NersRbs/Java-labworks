package ru.gunkin.model;

import java.time.LocalDate;

public record Notification(String header, String message, LocalDate date) {
    @Override
    public String toString() {
        return date + "\n" + header + ":\n" + message;
    }
}
