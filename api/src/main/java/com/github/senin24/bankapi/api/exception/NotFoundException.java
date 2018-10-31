package com.github.senin24.bankapi.api.exception;

public class NotFoundException extends RuntimeException {

    private Long id;

    public NotFoundException(Long id) {
        super();
        this.id = id;
    }
}
