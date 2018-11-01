package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Currency;
import com.github.senin24.bankapi.api.domain.Customer;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collection;

public interface AccountService {

    ResponseEntity<Account> findById(Long account_id) throws Exception;

    ResponseEntity<Account> findByCustomerIdAndId(Long customer_id, Long account_id);

    //    List<Account> findByCustomerId(Long customer_id);
    ResponseEntity<Collection<Account>> findByCustomerId(Long customer_id);


    Account create(Account account, Long customer_id);


    Account create(String accountName, Customer customer);

    Account create(String accountName, BigDecimal balance, Currency currency, Customer customer);

    void updateBalance(Account account, BigDecimal newBalance);
}
