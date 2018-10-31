package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Currency;
import com.github.senin24.bankapi.api.domain.Transact;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactService {

    ResponseEntity<Transact> findById(Long transact_id) throws Exception;

    List<Transact> findByAccountId(Long account_id);




    Transact create(String transactionName, BigDecimal amount, Date time, Currency currency
            , Account debitAccount, Account creditAccount);

}
