package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Currency;
import com.github.senin24.bankapi.api.domain.Customer;

import java.math.BigDecimal;

public interface AccountService {

    Account findByName(String accountName);

    Account create(String accountName, Customer customer);

    Account create(String accountName, BigDecimal balance, Currency currency, Customer customer);

    void updateBalance (Account account, BigDecimal newBalance);

}
