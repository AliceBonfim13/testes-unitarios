package com.alice.locadora.model;

public class ReservaInvalidException extends RuntimeException {
    public ReservaInvalidException(String message) {
        super(message);
    }
}
