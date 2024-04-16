package ru.gunkin.model;

import ru.gunkin.contracts.builder.users.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record User(UUID id, String firstName, String lastName, List<Notification> notifications,
                   Optional<String> address, Optional<Integer> passportNumber) {


    public static AddId builder() {
        return new Builder();
    }

    private static class Builder implements AddId, AddFirstName, AddLastName, AddNotifications, UserBuilder {
        private UUID id;
        private String firstName;
        private String lastName;
        private List<Notification> notifications;
        private Optional<String> address = Optional.empty();
        private Optional<Integer> passportNumber = Optional.empty();

        @Override
        public AddFirstName addId(UUID id) {
            this.id = id;
            return this;
        }


        @Override
        public AddLastName addFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        @Override
        public AddNotifications addLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        @Override
        public UserBuilder addNotifications(List<Notification> notifications) {
            this.notifications = notifications;
            return this;
        }

        @Override
        public UserBuilder addAddress(Optional<String> address) {
            this.address = address;
            return this;
        }

        @Override
        public UserBuilder addPassportNumber(Optional<Integer> passportNumber) {
            this.passportNumber = passportNumber;
            return this;
        }

        @Override
        public User build() {
            return new User(id, firstName, lastName, notifications, address, passportNumber);
        }
    }
}
