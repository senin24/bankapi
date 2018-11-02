package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;

import java.util.Collection;
import java.util.Optional;

public interface AccountService {

    Optional<Account> findById(Long account_id) throws Exception;

    Collection<Account> findByCustomerId(Long customer_id);

    Account create(Account account, Long customer_id);

    Account update(Account account);
}
