package com.github.senin24.bankapi.api.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private long inn;
    @NonNull
    private String description;

//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NonNull
    private Set<Account> accounts = new HashSet<>();

    public Customer(String name, long inn, String description) {
        this.name = name;
        this.inn = inn;
        this.description = description;
    }
}
