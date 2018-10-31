package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Currency;
import com.github.senin24.bankapi.api.domain.Customer;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    ResponseEntity<Account> findById(Long account_id) throws Exception;

    List<Account> findByCustomerId(Long customer_id);




    Account create(String accountName, Customer customer);

    Account create(String accountName, BigDecimal balance, Currency currency, Customer customer);

    void updateBalance (Account account, BigDecimal newBalance);
}
