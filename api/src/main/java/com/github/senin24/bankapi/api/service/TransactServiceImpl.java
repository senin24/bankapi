package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.*;
import com.github.senin24.bankapi.api.exception.AccountNotFoundException;
import com.github.senin24.bankapi.api.exception.CustomerNotFoundException;
import com.github.senin24.bankapi.api.repositories.AccountRepository;
import com.github.senin24.bankapi.api.repositories.CustomerRepository;
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

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public ResponseEntity<Transact> findById(Long transact_id) throws Exception {
        return transactionRepository.findById(transact_id).map(ResponseEntity::ok).orElseThrow(() -> new Exception());
    }

    @Override
    public List<Transact> findByAccountId(Long account_id) {
        List<Transact> transacts = new ArrayList<>();
//        transactionRepository.findByCreditAccountId(account_id).forEach(transacts::add);
//        transactionRepository.findByDebitAccountId(account_id).forEach(transacts::add);
        transacts.addAll(transactionRepository.findByCreditAccountId(account_id).orElseThrow(() -> new AccountNotFoundException(account_id)));
        transacts.addAll(transactionRepository.findByDebitAccountId(account_id).orElseThrow(() -> new AccountNotFoundException(account_id)));
        return transacts;
    }




    @Override
    public Transact create(Transact transact, Long customer_id, Long debit_account_id, Long creditAccountId) {
        customerRepository.findById(customer_id).orElseThrow(() -> new CustomerNotFoundException(customer_id));
        Account debitAccount = accountRepository.findById(debit_account_id).orElseThrow(() -> new AccountNotFoundException(debit_account_id));
        Account creditAccount = accountRepository.findById(creditAccountId).orElseThrow(() -> new AccountNotFoundException(creditAccountId));
        transact.setDebitAccount(debitAccount);
        transact.setCreditAccount(creditAccount);
        transact.setStartDate(new Date());
        Transact savedTransact = transactionRepository.save(transact);
        //Run Transaction
        if (debitAccount.getCurrency() == creditAccount.getCurrency()) {
            if ((debitAccount.getBalance().subtract(transact.getAmount()).compareTo(BigDecimal.ZERO)) > 0) {
                debitAccount.setBalance(debitAccount.getBalance().subtract(transact.getAmount()));
                creditAccount.setBalance(creditAccount.getBalance().add(transact.getAmount()));
                accountRepository.save(debitAccount);
                accountRepository.save(creditAccount);
                //Emulation delay transaction
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                transact.setFinishDate(new Date());
                transact.setStatus(Status.COMPLETE);
            }
            else {
                transact.setFinishDate(new Date());
                transact.setStatus(Status.REFUSE);
                transact.setDescription("Not enough money in the account number: " + debitAccount.getAccountNumber());
            }
        } else {
            transact.setFinishDate(new Date());
            transact.setStatus(Status.REFUSE);
            transact.setDescription(String.format(
                    "Currency accounts is not the same. Currency of debit account is '%s'. Currency of credit account is '%s'",
                    debitAccount.getCurrency(), creditAccount.getCurrency()));
        }
        return transactionRepository.save(savedTransact);
    }


    @Override
    public Transact create(String transactionName, BigDecimal amount, Date time, Currency currency, Account debitAccount, Account creditAccount) {
        throw new UnsupportedOperationException("Not implemented, yet");
//        return null;
    }

}
