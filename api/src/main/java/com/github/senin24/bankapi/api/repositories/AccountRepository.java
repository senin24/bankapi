package com.github.senin24.bankapi.api.repositories;

import com.github.senin24.bankapi.api.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
