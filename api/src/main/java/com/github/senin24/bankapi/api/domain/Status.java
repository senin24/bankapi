package com.github.senin24.bankapi.api.domain;

/**
 * Status of Transaction.
 */
public enum Status {

    DRAFT, REFUSE, COMPLETE;

    public static Status getDefault() {
        return DRAFT;
    }

}
