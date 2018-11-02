package com.github.senin24.bankapi.api.controller;

import com.github.senin24.bankapi.api.domain.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class IncRequestBody {
    private Long id, customerId, accountId, debitAccountId, creditAccountId;
    private String name, description, accountNumber, transactionName;
    private long inn;
    private BigDecimal balance, amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
