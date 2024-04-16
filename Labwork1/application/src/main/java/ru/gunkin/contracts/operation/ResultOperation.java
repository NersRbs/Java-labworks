package ru.gunkin.contracts.operation;

public interface ResultOperation {
    class Success implements ResultOperation {
        @Override
        public String toString() {
            return "Success";
        }
    }

    class Failure implements ResultOperation {
        private final String message;

        public Failure(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }
}
