package com.github.senin24.bankapi.api.exception;

public class TransactNotFoundException extends RuntimeException {
    public TransactNotFoundException(Long id) {
        super("Transaction not found, id: " + id.toString());
    }
}
