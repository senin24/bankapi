package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Currency;
import com.github.senin24.bankapi.api.domain.Transact;

import java.math.BigDecimal;
import java.util.Date;

public interface TransactService {

    Transact create(String transactionName, BigDecimal amount, Date time, Currency currency
            , Account debitAccount, Account creditAccount);

    Transact run (String transactionName);

    Transact run (Transact transact);
}
