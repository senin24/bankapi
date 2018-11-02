package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Customer;

import java.util.Collection;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> findById(Long id) throws Exception;

    Collection<Customer> findAllCustomers();

    Customer create(Customer customer);

    Customer update(Customer customer, Long customer_id);
}
