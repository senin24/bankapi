package com.github.senin24.bankapi.api.repositories;

import com.github.senin24.bankapi.api.domain.Transact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transact, Long> {
}
