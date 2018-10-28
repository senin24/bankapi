package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Currency;
import com.github.senin24.bankapi.api.domain.Customer;
import com.github.senin24.bankapi.api.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }




    @Override
    public Account findByName(String accountName) {
        throw new UnsupportedOperationException("Not implemented, yet");
    }

    @Override
    public Account create(String accountName, Customer customer) {
        throw new UnsupportedOperationException("Not implemented, yet");
//        return null;
    }

    @Override
    public Account create(String accountName, BigDecimal balance, Currency currency, Customer customer) {
        throw new UnsupportedOperationException("Not implemented, yet");
//        return null;
    }

    @Override
    public void updateBalance(Account account, BigDecimal newBalance) {
        throw new UnsupportedOperationException("Not implemented, yet");
    }
}
