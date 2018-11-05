package com.github.senin24.bankapi.api.repositories;

import com.github.senin24.bankapi.api.domain.Transact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transact, Long> {

    Optional<Collection<Transact>> findByCreditAccountId(Long account_id);
    Optional<Collection<Transact>> findByDebitAccountId(Long account_id);

}
