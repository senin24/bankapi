package com.github.senin24.bankapi.api.controller;

import com.github.senin24.bankapi.api.domain.Customer;
import com.github.senin24.bankapi.api.service.AccountService;
import com.github.senin24.bankapi.api.service.CustomerService;
import com.github.senin24.bankapi.api.service.TransactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/bankapi")
public class BankAPIController {

    private CustomerService customerService;
    private AccountService accountService;
    private TransactService transactService;

    @Autowired
    public BankAPIController(CustomerService customerService, AccountService accountService, TransactService transactService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.transactService = transactService;
    }

    @GetMapping(path = "/customer")
    ResponseEntity<Iterable<Customer>> getAllCustomers() {
//    ResponseEntity<Collection<Customer>> getAllCustomers() {
        return ResponseEntity.ok(this.customerService.findAllCustomers());
    }

    //TODO
    @GetMapping(path = "/customer", value = "/{name}")
    ResponseEntity<Iterable<Customer>> getCustomerByName() {
//    ResponseEntity<Collection<Customer>> getAllCustomers() {
        return ResponseEntity.ok(this.customerService.findAllCustomers());
    }


}
