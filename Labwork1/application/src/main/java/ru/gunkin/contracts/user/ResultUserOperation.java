package ru.gunkin.contracts.user;

public interface ResultUserOperation {
    class Success implements ResultUserOperation {
        @Override
        public String toString() {
            return "Success";
        }

    }
    class Failure implements ResultUserOperation {
        private final String message;

        public Failure(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }
    class CannotBeLogin extends Failure {
        public CannotBeLogin(String message) {
            super(message);
        }

    }
    class CannotBeCreate extends Failure {
        public CannotBeCreate(String message) {
            super(message);
        }

    }
    class CannotBeDeleted extends Failure {
        public CannotBeDeleted(String message) {
            super(message);
        }

    }
}
