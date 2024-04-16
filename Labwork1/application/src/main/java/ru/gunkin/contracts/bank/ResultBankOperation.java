package ru.gunkin.contracts.bank;

public interface ResultBankOperation {
    class Success implements ResultBankOperation {
        @Override
        public String toString() {
            return "Success";
        }
    }

    class Failure implements ResultBankOperation {
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
