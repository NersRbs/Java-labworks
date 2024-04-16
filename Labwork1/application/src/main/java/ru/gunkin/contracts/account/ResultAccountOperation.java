package ru.gunkin.contracts.account;

public interface ResultAccountOperation {
    class Success implements ResultAccountOperation {
        @Override
        public String toString() {
            return "Success";
        }

    }

    class Failure implements ResultAccountOperation {
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

    class CannotBeTopUp extends Failure {

        public CannotBeTopUp(String message) {
            super(message);
        }

    }

    class CannotBeWithdraw extends Failure {

        public CannotBeWithdraw(String message) {
            super(message);
        }

    }

    class CannotBeTransfer extends Failure {

        public CannotBeTransfer(String message) {
            super(message);
        }
    }

    class AmountMustBePositive extends Failure {
        public AmountMustBePositive() {
            super("Amount must be positive");
        }

    }

    class InsufficientFunds extends Failure {
        public InsufficientFunds() {
            super("Insufficient funds");
        }

    }
}