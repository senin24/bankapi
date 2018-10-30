package com.github.senin24.bankapi.api.controller;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Customer;
import com.github.senin24.bankapi.api.service.AccountService;
import com.github.senin24.bankapi.api.service.CustomerService;
import com.github.senin24.bankapi.api.service.TransactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerRestController {

    private CustomerService customerService;
    private AccountService accountService;
    private TransactService transactService;

    @Autowired
    public CustomerRestController(CustomerService customerService, AccountService accountService, TransactService transactService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.transactService = transactService;
    }

    @GetMapping
    ResponseEntity<Collection<Customer>> getCustomers() {
        return ResponseEntity.ok(this.customerService.findAllCustomers());
    }

    @GetMapping(value = "/{customer_id}/accounts")
    ResponseEntity<Collection<Account>> getAccountsByCustomerId(@PathVariable Long customer_id) {
        return ResponseEntity.ok(this.accountService.findByCustomerId(customer_id));
    }

    @GetMapping(value = "/{customer_id}")
    ResponseEntity<Customer> getCustomer(@PathVariable Long customer_id) throws Exception {
        return this.customerService.findById(customer_id);
    }




}
