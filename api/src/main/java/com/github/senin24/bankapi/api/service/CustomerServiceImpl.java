package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Customer;
import com.github.senin24.bankapi.api.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAllCustomers() {
//        return  Lists.newArrayList(customerRepository.findAll());
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public ResponseEntity<Customer> findById(Long id) throws Exception {
        return customerRepository.findById(id).map(ResponseEntity::ok).orElseThrow(() -> new Exception());
    }

    @Override
    public Customer create(String name, int inn, String description) {
        throw new UnsupportedOperationException("Not implemented, yet");
//        return null;
    }

    @Override
    public void addAccount(Customer customer, Account account) {
        throw new UnsupportedOperationException("Not implemented, yet");
    }
}
