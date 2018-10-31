package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Currency;
import com.github.senin24.bankapi.api.domain.Customer;
import com.github.senin24.bankapi.api.domain.Transact;
import com.github.senin24.bankapi.api.repositories.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactServiceImpl implements TransactService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    TransactionRepository transactionRepository;

    @Autowired
    public TransactServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public ResponseEntity<Transact> findById(Long transact_id) throws Exception {
        return transactionRepository.findById(transact_id).map(ResponseEntity::ok).orElseThrow(() -> new Exception());
    }

    @Override
    public List<Transact> findByAccountId(Long account_id) {
        List<Transact> transacts = new ArrayList<>();
        transactionRepository.findByCreditAccountId(account_id).forEach(transacts::add);
        transactionRepository.findByDebitAccountId(account_id).forEach(transacts::add);
        return transacts;
    }




    @Override
    public Transact create(String transactionName, BigDecimal amount, Date time, Currency currency, Account debitAccount, Account creditAccount) {
        throw new UnsupportedOperationException("Not implemented, yet");
//        return null;
    }

}
