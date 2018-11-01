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
        return this.customerService.findById(customer_id);
    }

    @GetMapping(value = "/customers/{customer_id}/accounts")
    ResponseEntity<Collection<Account>> getAccountsByCustomerId(@PathVariable Long customer_id) {
        return accountService.findByCustomerId(customer_id);
    }

    @GetMapping(value = "/customers/{customer_id}/accounts/{account_id}")
    ResponseEntity<Account> getAccountByCustomerAndId(@PathVariable Long customer_id, @PathVariable Long account_id) throws Exception {
        return this.accountService.findByCustomerIdAndId(customer_id, account_id);
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
    ResponseEntity<Customer> createCustomer(@RequestBody Customer c) {
        Customer customer = customerService.create(new Customer(c.getName(), c.getInn()));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @PostMapping(value = "/customers/{customer_id}/accounts")
    ResponseEntity<Account> createAccount(@RequestBody Account a, @PathVariable Long customer_id) {
        Account account = accountService.create(
                new Account(a.getAccountNumber(), a.getBalance(), a.getCurrency()), customer_id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/customers/{customer_id}/accounts/{debit_account_id}/transactions")
    ResponseEntity<Transact> createAndRunTransact(
            @PathVariable Long customer_id, @PathVariable Long debit_account_id, @RequestBody Transact t, @RequestBody Long creditAccountId) {
        Transact transact = transactService.create(new Transact(
                t.getTransactionName(), t.getAmount(), t.getCurrency()), customer_id, debit_account_id, creditAccountId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(transact.getId()).toUri();
        return ResponseEntity.created(location).build();
    }





}
