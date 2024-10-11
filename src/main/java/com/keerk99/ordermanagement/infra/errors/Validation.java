package com.keerk99.ordermanagement.infra.errors;

public class Validation extends RuntimeException {
    public Validation(String message) {
        super(message);
    }
}
