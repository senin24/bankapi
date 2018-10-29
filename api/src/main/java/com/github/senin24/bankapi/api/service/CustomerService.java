package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    Customer findByName(String name);

    Customer findByINN(int inn);

    Customer findByAccountNumber(String accountNumber);

    List<Customer> findAllCustomers();

    Customer create(String name, int inn, String description);

    void addAccounts(Customer customer, Set<Account> accounts);

    void addAccount(Customer customer, Account account);
}
