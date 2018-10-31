package com.github.senin24.bankapi.api.controller;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Customer;
import com.github.senin24.bankapi.api.domain.Transact;
import com.github.senin24.bankapi.api.service.AccountService;
import com.github.senin24.bankapi.api.service.CustomerService;
import com.github.senin24.bankapi.api.service.TransactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/v1")
public class BankApiRestController {

    private CustomerService customerService;
    private AccountService accountService;
    private TransactService transactService;

    @Autowired
    public BankApiRestController(CustomerService customerService, AccountService accountService, TransactService transactService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.transactService = transactService;
    }

    @GetMapping(path = "/customers")
    ResponseEntity<Collection<Customer>> getCustomers() {
        return ResponseEntity.ok(this.customerService.findAllCustomers());
    }

    @GetMapping(value = "/customers/{customer_id}")
    ResponseEntity<Customer> getCustomer(@PathVariable Long customer_id) throws Exception {
        return this.customerService.findById(customer_id);
    }

    @GetMapping(value = "/customers/{customer_id}/accounts")
    ResponseEntity<Collection<Account>> getAccountsByCustomerId(@PathVariable Long customer_id) {
        return ResponseEntity.ok(this.accountService.findByCustomerId(customer_id));
    }

    @GetMapping(value = "/accounts/{account_id}")
    ResponseEntity<Account> getAccountById(@PathVariable Long account_id) throws Exception {
        return this.accountService.findById(account_id);
    }

    @GetMapping(value = "/accounts/{account_id}/transactions")
    ResponseEntity<Collection<Transact>> getTransactionsByAccountId(@PathVariable Long account_id) {
        return ResponseEntity.ok(this.transactService.findByAccountId(account_id));
    }

    @GetMapping(value = "/transactions/{transact_id}")
    ResponseEntity<Transact> getTransactById(@PathVariable Long transact_id) throws Exception {
        return this.transactService.findById(transact_id);
    }


    @PostMapping(value = "/customers")
    ResponseEntity<Customer> createCustomer(@RequestBody Customer c) throws Exception {
        Customer customer = customerService.create(new Customer(c.getName()));

        return this.customerService.findById(customer_id);
    }




}
