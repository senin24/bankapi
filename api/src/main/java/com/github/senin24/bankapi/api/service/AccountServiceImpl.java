package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Currency;
import com.github.senin24.bankapi.api.domain.Customer;
import com.github.senin24.bankapi.api.exception.AccountNotFoundException;
import com.github.senin24.bankapi.api.exception.CustomerNotFoundException;
import com.github.senin24.bankapi.api.repositories.AccountRepository;
import com.github.senin24.bankapi.api.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<Account> findById(Long account_id) throws Exception {
        return accountRepository.findById(account_id).map(ResponseEntity::ok).orElseThrow(() -> new AccountNotFoundException(account_id));
    }

    @Override
    public ResponseEntity<Account> findByCustomerIdAndId(Long customer_id, Long account_id) {
        if (!customerRepository.existsById(customer_id)) throw new CustomerNotFoundException(customer_id);
        return accountRepository.findByCustomerIdAndId(customer_id, account_id).map(ResponseEntity::ok).orElseThrow(() -> new AccountNotFoundException(account_id));
    }

    @Override
    public ResponseEntity<Collection<Account>> findByCustomerId(Long customer_id) {
//        if (!customerRepository.existsById(customer_id)){
//            throw new CustomerNotFoundException(customer_id);
//        }
//        List<Account> accounts = new ArrayList<>();
//        accountRepository.findByCustomerId(customer_id).forEach(accounts::add);
        return accountRepository.findByCustomerId(customer_id).map(ResponseEntity::ok).orElseThrow(() -> new CustomerNotFoundException(customer_id));
//        return accounts;
    }

    @Override
    public Account create(Account account, Long customer_id) {
        Customer customer = customerRepository.findById(customer_id).orElseThrow(() -> new CustomerNotFoundException(customer_id));
        account.setCustomer(customer);
        return accountRepository.save(account);
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
