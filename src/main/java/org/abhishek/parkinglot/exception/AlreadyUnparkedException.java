package org.abhishek.parkinglot.exception;

public class AlreadyUnparkedException extends  RuntimeException {
    public AlreadyUnparkedException(String message) {
        super(message);
    }
}
