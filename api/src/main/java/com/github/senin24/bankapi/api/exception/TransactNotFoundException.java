package com.github.senin24.bankapi.api.exception;

public class TransactNotFoundException extends NotFoundException {
    public TransactNotFoundException(Long id) {
        super(id);
    }
}
