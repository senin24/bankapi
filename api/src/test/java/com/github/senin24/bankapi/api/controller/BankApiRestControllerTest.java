package com.github.senin24.bankapi.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.senin24.bankapi.api.domain.*;
import com.github.senin24.bankapi.api.service.AccountService;
import com.github.senin24.bankapi.api.service.CustomerService;
import com.github.senin24.bankapi.api.service.TransactService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.containsString;

//TODO Add Failed tests

@RunWith(SpringRunner.class)
@WebMvcTest(BankApiRestController.class)
public class BankApiRestControllerTest {

    private static final Long TEST_ID_01 = 1L;
    private static final Long TEST_ID_02 = 2L;
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactService transactService;

    @Test
    public void getCustomersSuccess() throws Exception {
        Customer customer01 = new Customer("Клиент01", 6201234567891L, "тестовый клиент");
        customer01.setId(1L);
        Customer customer02 = new Customer("Клиент02", 6201234567892L, "тестовый клиент");
        customer02.setId(2L);
        List<Customer> customers = Lists.newArrayList(customer01, customer02);
        String json = mapper.writeValueAsString(customers);
        System.out.println(json);
        given(this.customerService.findAllCustomers()).willReturn(
                Lists.newArrayList(customer01, customer02));

        this.mockMvc.perform(get("/v1/customers").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(json))
                .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @Test
    public void getCustomerByIdSuccess() throws Exception {
        Customer customer02 = new Customer("Клиент02", 6201234567892L, "тестовый клиент");
        customer02.setId(TEST_ID_02);
        String json = mapper.writeValueAsString(customer02);
        System.out.println(json);
        given(this.customerService.findById(TEST_ID_02)).willReturn(Optional.ofNullable(customer02));

        this.mockMvc.perform(get("/v1/customers/{customer_id}", TEST_ID_02).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    public void getAccountsByCustomerIdSuccess() throws Exception {
        Customer customer01 = new Customer("Клиент01", 6201234567891L, "тестовый клиент");
        customer01.setId(TEST_ID_01);
        Account account01 = new Account("00001_RUB_customer01", new BigDecimal(1000), Currency.RUB, customer01);
        account01.setId(1L);
        Account account02 = new Account("00002_EUR_customer01", new BigDecimal(1000), Currency.EUR, customer01);
        account02.setId(2L);
        Account account03 = new Account("00003_BTC_customer01", new BigDecimal(1000), Currency.BTC, customer01);
        account03.setId(3L);
        List<Account> accounts = Lists.newArrayList(account01, account02, account03);
        String json = mapper.writeValueAsString(accounts);
        System.out.println(json);
        given(this.accountService.findByCustomerId(TEST_ID_01)).willReturn(accounts);

        this.mockMvc.perform(get("/v1/customers/{customer_id}/accounts", TEST_ID_01).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(json))
                .andExpect(jsonPath("$.*", hasSize(3)));
    }

    @Test
    public void getAccountByIdSuccess() throws Exception {
        Customer customer01 = new Customer("Клиент01", 6201234567891L, "тестовый клиент");
        customer01.setId(TEST_ID_01);
        Account account01 = new Account("00001_RUB_customer01", new BigDecimal(1000), Currency.RUB, customer01);
        account01.setId(1L);
        String json = mapper.writeValueAsString(account01);
        System.out.println(json);
        given(this.accountService.findById(TEST_ID_01)).willReturn(Optional.ofNullable(account01));

        this.mockMvc.perform(get("/v1/accounts/{account_id}", TEST_ID_01).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    public void getTransactionsByAccountIdSuccess() throws Exception {
        Customer customer01 = new Customer("Клиент01", 6201234567891L, "тестовый клиент");
        Account account01 = new Account("00001_RUB_customer01", new BigDecimal(1000), Currency.RUB, customer01);
        Account account02 = new Account("00002_RUB_customer01", new BigDecimal(1000), Currency.RUB, customer01);
        Transact transact01 = new Transact("Транзакция Счет01 RUB Счет02", new BigDecimal(100), Currency.RUB, account01, account02);
        Transact transact02 = new Transact("Транзакция Обратно Счет02 RUB Счет01", new BigDecimal(100), Currency.RUB, account02, account01);
        List<Transact> transacts = Lists.newArrayList(transact01, transact02);
        String json = mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writeValueAsString(transacts);
        System.out.println(json);
        given(this.transactService.findByAccountId(TEST_ID_01)).willReturn(transacts);

        this.mockMvc.perform(get("/v1/accounts/{account_id}/transactions", TEST_ID_01).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(json))
                .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @Test
    public void getTransactByIdSuccess() throws Exception {
        Customer customer01 = new Customer("Клиент01", 6201234567891L, "тестовый клиент");
        Account account01 = new Account("00001_RUB_customer01", new BigDecimal(1000), Currency.RUB, customer01);
        Account account02 = new Account("00002_RUB_customer01", new BigDecimal(1000), Currency.RUB, customer01);
        Transact transact01 = new Transact("Транзакция Счет01 RUB Счет02", new BigDecimal(100), Currency.RUB, account01, account02);
        String json = mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writeValueAsString(transact01);
        System.out.println(json);
        given(this.transactService.findById(TEST_ID_01)).willReturn(Optional.ofNullable(transact01));

        this.mockMvc.perform(get("/v1/transactions/{transact_id}", TEST_ID_01).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    public void postCustomerSuccess() throws Exception {
        Customer customer01 = new Customer("Клиент01", 6201234567891L);
        String json = mapper.writeValueAsString(customer01);
        customer01.setId(TEST_ID_01);
        given(this.customerService.create(new Customer("Клиент01", 6201234567891L))).willReturn(customer01);

        mockMvc.perform(post("/v1/customers").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/v1/customers/" + TEST_ID_01)));
    }

    @Test
    public void postAccountSuccess() throws Exception {
        Customer customer01 = new Customer("Клиент01", 6201234567891L, "тестовый клиент");
        customer01.setId(TEST_ID_01);
        RB rb = new RB();
        rb.setAccountNumber("00001_BTC_customer_id=1_FromRest");
        rb.setBalance(new BigDecimal(777));
        rb.setCurrency(Currency.BTC);
        rb.setCustomerId(TEST_ID_01);
        String json = mapper.writeValueAsString(rb);
        System.out.println(json);
        given(this.accountService.create(new Account(rb.getAccountNumber(), rb.getBalance(), rb.getCurrency()), rb.getCustomerId()))
                .willReturn(new Account(TEST_ID_01, rb.getAccountNumber(), "", rb.getBalance(), rb.getCurrency(), customer01));

        mockMvc.perform(post("/v1/accounts").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/v1/accounts/" + TEST_ID_01)));
    }

    //TODO fix (https://stackoverflow.com/questions/16170572/unable-to-mock-service-class-in-spring-mvc-controller-tests)
//    @Test
    public void postAndRunTransactSuccess() throws Exception {
        Customer customer01 = new Customer("Клиент01", 6201234567891L, "тестовый клиент");
        customer01.setId(TEST_ID_01);
        Account account01 = new Account("00001_RUB_customer01", new BigDecimal(1000), Currency.BTC, customer01);
        account01.setId(TEST_ID_01);
        Account account02 = new Account("00002_RUB_customer01", new BigDecimal(1000), Currency.BTC, customer01);
        account02.setId(TEST_ID_02);
        RB rb = new RB();
        rb.setTransactionName("Транзакция BTC FromRest");
        rb.setAmount(new BigDecimal(100));
        rb.setCurrency(Currency.BTC);
        rb.setDebitAccountId(3L);
        rb.setCreditAccountId(7L);
        String json = mapper.writeValueAsString(rb);
        System.out.println(json);
        Transact mockTransact = new Transact(rb.getTransactionName(), rb.getAmount(), rb.getCurrency(), account01, account02);
        mockTransact.setId(TEST_ID_01);
        mockTransact.setStatus(Status.COMPLETE);
        //TODO mock transactService.create() not work! WHY!?
        given(this.transactService.create(
                new Transact(rb.getTransactionName(), rb.getAmount(), rb.getCurrency())
                , rb.getDebitAccountId(), rb.getCreditAccountId()
        )).willReturn(mockTransact);

        this.mockMvc.perform(post("/v1/transactions").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/v1/transactions/" + TEST_ID_01)));
    }

    @Test
    public void putCustomerSuccess() throws Exception {
        RB rb = new RB();
        rb.setName("Client01-update");
        rb.setDescription("Description-update");
        String json = mapper.writeValueAsString(rb);
        System.out.println(json);
        given(this.customerService.update("", "", 0L))
                .willReturn(new Customer(TEST_ID_01, rb.getName(), rb.getDescription(), 6201234567891L));

        this.mockMvc.perform(put("/v1/customers/{customer_id}", TEST_ID_01).contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/v1/customers/" + TEST_ID_01)));
    }

    @Test
    public void putAccountSuccess() throws Exception {
        RB rb = new RB();
        rb.setDescription("Description-Account-update");
        String json = mapper.writeValueAsString(rb);
        System.out.println(json);
        given(this.accountService.update(rb.getDescription(), 1L))
                .willReturn(new Account("00001_RUB_customer01", new BigDecimal(1000), Currency.BTC, null));

        this.mockMvc.perform(put("/v1/accounts/{account_id}", TEST_ID_01).contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/v1/accounts/" + TEST_ID_01)));
    }

    @Test
    public void putTransactionSuccess() throws Exception {
        Transact transact01 = new Transact("Транзакция Счет01 RUB Счет02", new BigDecimal(100), Currency.RUB, null, null);
        RB rb = new RB();
        rb.setDescription("Description-Transaction-update");
        String json = mapper.writeValueAsString(rb);
        System.out.println(json);
        given(this.transactService.update(rb.getDescription(), 1L))
                .willReturn(
                        new Transact("Транзакция Счет01 RUB Счет02", new BigDecimal(100), Currency.RUB, null, null)
                );

        this.mockMvc.perform(put("/v1/transactions/{transact_id}", TEST_ID_01).contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/v1/transactions/" + TEST_ID_01)));
    }
}