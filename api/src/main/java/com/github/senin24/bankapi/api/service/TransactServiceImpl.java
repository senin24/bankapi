package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Currency;
import com.github.senin24.bankapi.api.domain.Transact;
import com.github.senin24.bankapi.api.repositories.TransationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class TransactServiceImpl implements TransactService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    TransationRepository transationRepository;

    @Autowired
    public TransactServiceImpl(TransationRepository transationRepository) {
        this.transationRepository = transationRepository;
    }




    @Override
    public Transact create(String transactionName, BigDecimal amount, Date time, Currency currency, Account debitAccount, Account creditAccount) {
        throw new UnsupportedOperationException("Not implemented, yet");
//        return null;
    }

    @Override
    public Transact run(String transactionName) {
        throw new UnsupportedOperationException("Not implemented, yet");
//        return null;
    }

    @Override
    public Transact run(Transact transact) {
        throw new UnsupportedOperationException("Not implemented, yet");
//        return null;
    }
}
