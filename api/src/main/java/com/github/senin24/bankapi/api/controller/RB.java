package com.github.senin24.bankapi.api.controller;

import com.github.senin24.bankapi.api.domain.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

/**
 * Class for incoming Request Body.
 */
@Data
@NoArgsConstructor
public class RB {
    private Long customerId, debitAccountId, creditAccountId;
    private String name, description, accountNumber, transactionName;
    private BigDecimal balance, amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
