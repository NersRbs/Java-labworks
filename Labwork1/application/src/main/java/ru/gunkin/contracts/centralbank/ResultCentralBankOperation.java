package ru.gunkin.contracts.centralbank;

public interface ResultCentralBankOperation {
    class Success implements ResultCentralBankOperation {
        @Override
        public String toString() {
            return "Success";
        }

    }
    class Failure implements ResultCentralBankOperation {
        private final String message;

        public Failure(String message) {
            this.message = message;
        }
        @Override
        public String toString() {
            return message;
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
    class CannotBeTransfer extends Failure {
        public CannotBeTransfer(String message) {
            super(message);
        }

    }
}
