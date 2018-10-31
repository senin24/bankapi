package com.github.senin24.bankapi.api.exception;

public class AccountNotFoundException extends NotFoundException {
    public AccountNotFoundException(Long id) {
        super(id);
    }
}
