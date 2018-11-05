package com.github.senin24.bankapi.api.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id) {
        super("Customer not found, id: " + id.toString());
    }
}
