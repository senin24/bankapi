package com.github.senin24.bankapi.api.init;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Currency;
import com.github.senin24.bankapi.api.domain.Customer;
import com.github.senin24.bankapi.api.domain.Transact;
import com.github.senin24.bankapi.api.repositories.AccountRepository;
import com.github.senin24.bankapi.api.repositories.CustomerRepository;
import com.github.senin24.bankapi.api.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitDirectInBD implements ApplicationRunner {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    private TransactionRepository transactService;

    @Autowired
    public DataInitDirectInBD(CustomerRepository customerRepository, AccountRepository accountRepository, TransactionRepository transactService) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transactService = transactService;
    }

    @Override
    public void run(ApplicationArguments args) {
        Customer customer01 = customerRepository.save(new Customer("Клиент01", 6201234567891L, "тестовый клиент"));
        Customer customer02 = customerRepository.save(new Customer("Клиент02", 6201234567892L, "тестовый клиент"));
        Customer customer03 = customerRepository.save(new Customer("Клиент03", 6201234567893L, "тестовый клиент"));
        Customer customer04 = customerRepository.save(new Customer("Клиент04", 6201234567894L, "тестовый клиент"));
        Customer customer05 = customerRepository.save(new Customer("Клиент05", 6201234567895L, "тестовый клиент"));

        Account account01 = accountRepository.save(new Account("00001_RUB_customer01", new BigDecimal(1000), Currency.RUB, customer01));
        Account account02 = accountRepository.save(new Account("00002_EUR_customer01", new BigDecimal(1000), Currency.EUR, customer01));
        Account account03 = accountRepository.save(new Account("00003_BTC_customer01", new BigDecimal(1000), Currency.BTC, customer01));
        Account account04 = accountRepository.save(new Account("00004_USD_customer01", new BigDecimal(1000), Currency.USD, customer01));
        Account account05 = accountRepository.save(new Account("00001_RUB_customer02", new BigDecimal(0), Currency.RUB, customer02));
        Account account06 = accountRepository.save(new Account("00001_USD_customer03", new BigDecimal(0), Currency.USD, customer03));
        Account account07 = accountRepository.save(new Account("00001_BTC_customer04", new BigDecimal(0), Currency.BTC, customer04));
        Account account08 = accountRepository.save(new Account("00001_EUR_customer05", new BigDecimal(0), Currency.EUR, customer05));

        transactService.save(new Transact("Транзакция Клиент01 RUB Клиент02", new BigDecimal(100), Currency.RUB, account01, account05));
        transactService.save(new Transact("Транзакция Клиент01 USD Клиент03", new BigDecimal(100), Currency.USD, account02, account06));
        transactService.save(new Transact("Транзакция Клиент01 BTC Клиент04", new BigDecimal(100), Currency.BTC, account03, account07));
        transactService.save(new Transact("Транзакция Клиент01 EUR Клиент05", new BigDecimal(100), Currency.EUR, account04, account08));

        transactService.save(new Transact("Транзакция Обратно Клиент02 RUB Клиент01", new BigDecimal(100), Currency.RUB, account05, account01));
        transactService.save(new Transact("Транзакция Обратно Клиент03 USD Клиент01", new BigDecimal(100), Currency.USD, account06, account02));
        transactService.save(new Transact("Транзакция Обратно Клиент04 BTC Клиент01", new BigDecimal(100), Currency.BTC, account07, account03));
        transactService.save(new Transact("Транзакция Обратно Клиент05 EUR Клиент01", new BigDecimal(100), Currency.EUR, account08, account04));
    }

}
