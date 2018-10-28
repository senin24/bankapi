package com.github.senin24.bankapi.api.repositories;

import com.github.senin24.bankapi.api.domain.Transact;
import org.springframework.data.repository.CrudRepository;

public interface TransationRepository extends CrudRepository<Transact, Long> {
}
