package com.github.senin24.bankapi.api.init;

import com.github.senin24.bankapi.api.domain.Account;
import com.github.senin24.bankapi.api.domain.Currency;
import com.github.senin24.bankapi.api.domain.Customer;
import com.github.senin24.bankapi.api.repositories.AccountRepository;
import com.github.senin24.bankapi.api.repositories.CustomerRepository;
import com.github.senin24.bankapi.api.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataInit implements ApplicationRunner {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    private TransactionRepository transactService;

    @Autowired
    public DataInit(CustomerRepository customerRepository, AccountRepository accountRepository, TransactionRepository transactService) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transactService = transactService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Customer customer01 = new Customer("Клиент01", 6201234567891L, "тестовый клиент");
        Customer customer02 = new Customer("Клиент02", 6201234567892L, "тестовый клиент");
        Customer customer03 = new Customer("Клиент03", 6201234567893L, "тестовый клиент");
        Customer customer04 = new Customer("Клиент04", 6201234567894L, "тестовый клиент");
        Customer customer05 = new Customer("Клиент05", 6201234567895L, "тестовый клиент");

        customer01 = customerRepository.save(customer01);
        customer02 = customerRepository.save(customer02);
        customer03 = customerRepository.save(customer03);
        customer04 = customerRepository.save(customer04);
        customer05 = customerRepository.save(customer05);

//        Account account01Customer01 = new Account("00001_RUB_customer01", new BigDecimal(1000), Currency.RUB, customer01);
//        Account account02Customer01 = new Account("00002_EUR_customer01", new BigDecimal(1000), Currency.EUR, customer01);
//        Account account03Customer01 = new Account("00003_BTC_customer01", new BigDecimal(1000), Currency.BTC, customer01);
//        Account account04Customer01 = new Account("00004_USD_customer01", new BigDecimal(1000), Currency.USD, customer01);
//        Account account01Customer02 = new Account("00001_RUB_customer02", new BigDecimal(0), Currency.RUB, customer02);
//        Account account01Customer03 = new Account("00001_USD_customer03", new BigDecimal(0), Currency.USD, customer03);
//        Account account01Customer04 = new Account("00001_BTC_customer04", new BigDecimal(0), Currency.BTC, customer04);
//        Account account01Customer05 = new Account("00001_EUR_customer05", new BigDecimal(0), Currency.EUR, customer05);

        accountRepository.saveAll(Stream.of(
                new Account("00001_RUB_customer01", new BigDecimal(1000), Currency.RUB, customer01),
                new Account("00002_EUR_customer01", new BigDecimal(1000), Currency.EUR, customer01),
                new Account("00003_BTC_customer01", new BigDecimal(1000), Currency.BTC, customer01),
                new Account("00004_USD_customer01", new BigDecimal(1000), Currency.USD, customer01),
                new Account("00001_RUB_customer02", new BigDecimal(0), Currency.RUB, customer02),
                new Account("00001_USD_customer03", new BigDecimal(0), Currency.USD, customer03),
                new Account("00001_BTC_customer04", new BigDecimal(0), Currency.BTC, customer04),
                new Account("00001_EUR_customer05", new BigDecimal(0), Currency.EUR, customer05)
        ).collect(Collectors.toList()));

    }

}
