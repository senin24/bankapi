package com.github.senin24.bankapi.api.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name, description;
    private long inn;

    public Customer(String name, long inn) {
        this.name = name;
        this.inn = inn;
        this.description = "";
    }

    public Customer(String name, long inn, String description) {
        this.name = name;
        this.inn = inn;
        this.description = description;
    }
}
