package com.github.senin24.bankapi.api.repositories;

import com.github.senin24.bankapi.api.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
