package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Customer;
import com.github.senin24.bankapi.api.exception.AccountNotFoundException;
import com.github.senin24.bankapi.api.exception.CustomerNotFoundException;
import com.github.senin24.bankapi.api.repositories.AccountRepository;
import com.github.senin24.bankapi.api.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Account> findById(Long account_id){
        return accountRepository.findById(account_id);
    }

    @Override
    public Collection<Account> findByCustomerId(Long customer_id) {
        customerRepository.findById(customer_id).orElseThrow(() -> new CustomerNotFoundException(customer_id));
        return accountRepository.findByCustomerId(customer_id);
    }

    @Override
    public Account create(Account account, Long customer_id) {
        Customer customer = customerRepository.findById(customer_id).orElseThrow(() -> new CustomerNotFoundException(customer_id));
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    @Override
    public Account update(String description, Long account_id) {
        Account account = accountRepository.findById(account_id).orElseThrow(() -> new AccountNotFoundException(account_id));
        if (!description.isEmpty()) account.setDescription(description);
        return accountRepository.save(account);
    }
}
