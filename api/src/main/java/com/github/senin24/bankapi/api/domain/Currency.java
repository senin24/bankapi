package com.github.senin24.bankapi.api.domain;

public enum Currency {

    USD, EUR, RUB, BTC;

    public static Currency getDefault() {
        return RUB;
    }
}
