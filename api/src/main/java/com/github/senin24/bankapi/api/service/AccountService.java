package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Currency;
import com.github.senin24.bankapi.api.domain.Customer;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface AccountService {

    List<Account> findByCustomerId(Long id);

    Account findByName(String accountName);

    Account create(String accountName, Customer customer);

    Account create(String accountName, BigDecimal balance, Currency currency, Customer customer);

    void updateBalance (Account account, BigDecimal newBalance);

}
