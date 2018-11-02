package com.github.senin24.bankapi.api.repositories;

import com.github.senin24.bankapi.api.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

//    Iterable<Account> findByCustomerId(Long customer_id);
    Collection<Account> findByCustomerId(Long customer_id);

    Optional<Account> findByCustomerIdAndId(Long customer_id, Long account_id);
}
