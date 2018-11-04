package com.github.senin24.bankapi.api.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String accountNumber, description;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<Transact> transacts = new HashSet<>();

    //for test-seeding
    public Account(String accountNumber, BigDecimal balance, Currency currency, Customer customer) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.customer = customer;
    }

    //for PostMapping
    public Account(String accountNumber, BigDecimal balance, Currency currency) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
    }
}
