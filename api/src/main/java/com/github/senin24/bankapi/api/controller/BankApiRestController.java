package com.github.senin24.bankapi.api.controller;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Customer;
import com.github.senin24.bankapi.api.domain.Transact;
import com.github.senin24.bankapi.api.exception.AccountNotFoundException;
import com.github.senin24.bankapi.api.exception.CustomerNotFoundException;
import com.github.senin24.bankapi.api.exception.TransactNotFoundException;
import com.github.senin24.bankapi.api.service.AccountService;
import com.github.senin24.bankapi.api.service.CustomerService;
import com.github.senin24.bankapi.api.service.TransactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        return customerService.findById(customer_id).map(ResponseEntity::ok).orElseThrow(() -> new CustomerNotFoundException(customer_id));
    }

    @GetMapping(value = "/customers/{customer_id}/accounts")
    ResponseEntity<Collection<Account>> getAccountsByCustomerId(@PathVariable Long customer_id) {
        return ResponseEntity.ok(accountService.findByCustomerId(customer_id));
    }

    @GetMapping(value = "/accounts/{account_id}")
    ResponseEntity<Account> getAccountById(@PathVariable Long account_id) throws Exception {
        return accountService.findById(account_id).map(ResponseEntity::ok).orElseThrow(() -> new AccountNotFoundException(account_id));
    }

    @GetMapping(value = "/accounts/{account_id}/transactions")
    ResponseEntity<Collection<Transact>> getTransactionsByAccountId(@PathVariable Long account_id) {
        return ResponseEntity.ok(transactService.findByAccountId(account_id));
    }

    @GetMapping(value = "/transactions/{transact_id}")
    ResponseEntity<Transact> getTransactById(@PathVariable Long transact_id) throws Exception {
        return transactService.findById(transact_id).map(ResponseEntity::ok).orElseThrow(() -> new TransactNotFoundException(transact_id));
    }



    @PostMapping(value = "/customers")
    ResponseEntity<Customer> createCustomer(@RequestBody Customer c) {
        Customer customer = customerService.create(new Customer(c.getName(), c.getInn()));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @PostMapping(value = "/accounts")
    ResponseEntity<Account> createAccount(@RequestBody IncRequestBody rb) {
        Account account = accountService.create(
                new Account(rb.getAccountNumber(), rb.getBalance(), rb.getCurrency()), rb.getCustomerId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/transactions")
    ResponseEntity<Transact> createAndRunTransact(@RequestBody IncRequestBody rb) {
        Transact transact = transactService.create(new Transact(
                rb.getTransactionName(), rb.getAmount(), rb.getCurrency()), rb.getDebitAccountId(), rb.getCreditAccountId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(transact.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @PutMapping(value = "/customers/{customer_id}")
    ResponseEntity<Customer> updateCustomer(@RequestBody IncRequestBody rb, @PathVariable Long customer_id) {
        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        Customer customer = new Customer();
        customer.setDescription(rb.getDescription());
        return ResponseEntity.created(selfLink).body(customerService.update(customer, customer_id));
    }







}
