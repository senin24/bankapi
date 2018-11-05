package com.github.senin24.bankapi.api.service;

import com.github.senin24.bankapi.api.domain.*;
import com.github.senin24.bankapi.api.exception.AccountNotFoundException;
import com.github.senin24.bankapi.api.exception.TransactNotFoundException;
import com.github.senin24.bankapi.api.exception.TransactRefusedException;
import com.github.senin24.bankapi.api.repositories.AccountRepository;
import com.github.senin24.bankapi.api.repositories.CustomerRepository;
import com.github.senin24.bankapi.api.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactServiceImpl implements TransactService {

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
    public Optional<Transact> findById(Long transact_id) throws Exception {
        return transactionRepository.findById(transact_id);
    }

    @Override
    public List<Transact> findByAccountId(Long account_id) {
        List<Transact> transacts = new ArrayList<>();
        transacts.addAll(transactionRepository.findByCreditAccountId(account_id).orElseThrow(() -> new AccountNotFoundException(account_id)));
        transacts.addAll(transactionRepository.findByDebitAccountId(account_id).orElseThrow(() -> new AccountNotFoundException(account_id)));
        return transacts;
    }

    /**
     * Saved transact if find debitAccount and creditAccount.
     * If currency and balance in accounts correct, then run transaction and set status COMPLETE.
     * Or set status REFUSE. Transaction saved with any status allways.
     *
     * @param transact         object of Transact class
     * @param debit_account_id Long id of debit account
     * @param creditAccountId  Long id of credit account
     * @return Transact object with any status
     */
    @Override
    public Transact create(Transact transact, Long debit_account_id, Long creditAccountId) {
        Account debitAccount = accountRepository.findById(debit_account_id).orElseThrow(() -> new AccountNotFoundException(debit_account_id));
        Account creditAccount = accountRepository.findById(creditAccountId).orElseThrow(() -> new AccountNotFoundException(creditAccountId));
        transact.setDebitAccount(debitAccount);
        transact.setCreditAccount(creditAccount);
        transact.setStartDate(new Date());
        Transact savedTransact = transactionRepository.save(transact);
        //Run Transaction
        //TODO code not clear?..
        if (debitAccount.getCurrency() == creditAccount.getCurrency()) {
            if (debitAccount.getCurrency() == savedTransact.getCurrency()) {
                if ((debitAccount.getBalance().subtract(savedTransact.getAmount()).compareTo(BigDecimal.ZERO)) > 0) {
                    runTransact(savedTransact, debitAccount, creditAccount);
                } else {
                    savedTransact.setFinishDate(new Date());
                    savedTransact.setStatus(Status.REFUSE);
                    savedTransact.setDescription(String.format("Not enough money in the account number '%s'. " +
                            "Transaction saved with id '%s' and with  status '%s',",
                            debitAccount.getAccountNumber(), savedTransact.getId(), savedTransact.getStatus()));
                    transactionRepository.save(savedTransact);
                    throw  new TransactRefusedException (savedTransact.getDescription());
                }
            } else {
                savedTransact.setFinishDate(new Date());
                savedTransact.setStatus(Status.REFUSE);
                savedTransact.setDescription(String.format(
                        "Currency in Accounts and in Transaction not the same. Currency of accounts is '%s'. Currency of transact is '%s'. " +
                                "Transaction saved with id '%s' and with  status '%s'",
                        debitAccount.getCurrency(), savedTransact.getCurrency(), savedTransact.getId(), savedTransact.getStatus()));
                transactionRepository.save(savedTransact);
                throw  new TransactRefusedException (savedTransact.getDescription());
            }
        } else {
            savedTransact.setFinishDate(new Date());
            savedTransact.setStatus(Status.REFUSE);
            savedTransact.setDescription(String.format(
                    "Currency accounts is not the same. Currency of debit account is '%s'. Currency of credit account is '%s'. " +
                            "Transaction saved with id '%s' and with status '%s'",
                    debitAccount.getCurrency(), creditAccount.getCurrency(), savedTransact.getId(), savedTransact.getStatus()));
            transactionRepository.save(savedTransact);
            throw  new TransactRefusedException (savedTransact.getDescription());
        }
        return transactionRepository.save(savedTransact);
    }

    /**
     * Rolback if method throws any exceptions
     *
     * @param transact
     * @param debitAccount
     * @param creditAccount
     */
    @Transactional(rollbackFor = Exception.class)
    public void runTransact(Transact transact, Account debitAccount, Account creditAccount) {
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

    @Override
    public Transact update(String description, Long transact_id) {
        Transact transact = transactionRepository.findById(transact_id).orElseThrow(() -> new TransactNotFoundException(transact_id));
        if (!description.isEmpty()) transact.setDescription(description);
        return transactionRepository.save(transact);
    }
}
