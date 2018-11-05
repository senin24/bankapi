package com.github.senin24.bankapi.api.exception;

public class TransactRefusedException extends RuntimeException {
    public TransactRefusedException(String reason) {
        super("Transaction saved but REFUSED! Reason: " + reason);
    }
}
