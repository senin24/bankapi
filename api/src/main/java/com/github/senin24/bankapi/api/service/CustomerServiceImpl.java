package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Customer;
import com.github.senin24.bankapi.api.exception.CustomerNotFoundException;
import com.github.senin24.bankapi.api.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> findById(Long customer_id) throws Exception {
        return customerRepository.findById(customer_id);
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }


    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer, Long customer_id) {
        Customer customer2 = customerRepository.findById(customer_id).orElseThrow(() -> new CustomerNotFoundException(customer_id));
        customer2.setDescription(customer.getDescription());
        return customerRepository.save(customer2);
//
//        return customerRepository.findById(customer_id).map(existing -> {
//            customerRepository.save(existing)
//        }).orElseThrow(() -> new CustomerNotFoundException(customer_id)));
//
////        return null;
    }


}
