package com.github.senin24.bankapi.api.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super("Account not found, id: " + id.toString());
    }
}
