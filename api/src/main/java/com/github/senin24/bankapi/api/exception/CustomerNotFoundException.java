package com.github.senin24.bankapi.api.exception;

public class CustomerNotFoundException extends NotFoundException {

    public CustomerNotFoundException(Long id) {
        super(id);
    }
}
