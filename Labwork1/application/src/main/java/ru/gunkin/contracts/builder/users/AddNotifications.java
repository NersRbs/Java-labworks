package ru.gunkin.contracts.builder.users;

import ru.gunkin.model.Notification;

import java.util.List;

public interface AddNotifications {
    UserBuilder addNotifications(List<Notification> notifications);
}
