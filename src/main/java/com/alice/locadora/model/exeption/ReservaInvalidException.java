package com.alice.locadora.model.exeption;

public class ReservaInvalidException extends RuntimeException {
    public ReservaInvalidException(String message) {
        super(message);
    }
}
