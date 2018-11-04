package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Transact;

import java.util.Collection;
import java.util.Optional;

public interface TransactService {

    Optional<Transact> findById(Long transact_id) throws Exception;

    Collection<Transact> findByAccountId(Long account_id);

    Transact create(Transact transact, Long debit_account_id, Long creditAccountId);

    Transact update(String description, Long transact_id);
}
