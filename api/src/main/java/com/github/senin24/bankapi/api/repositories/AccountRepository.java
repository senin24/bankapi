package com.github.senin24.bankapi.api.repositories;

import com.github.senin24.bankapi.api.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Iterable<Account> findByCustomerId(Long customer_id);
}
