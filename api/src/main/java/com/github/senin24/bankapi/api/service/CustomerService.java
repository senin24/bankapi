package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    ResponseEntity<Customer> findById(Long id) throws Exception;

    List<Customer> findAllCustomers();



    void addAccount(Customer customer, Account account);

    Customer create(Customer customer);
}
