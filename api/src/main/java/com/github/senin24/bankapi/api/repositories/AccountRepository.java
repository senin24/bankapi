package com.github.senin24.bankapi.api.repositories;

import com.github.senin24.bankapi.api.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Collection<Account> findByCustomerId(Long customer_id);

}
