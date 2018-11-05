package com.github.senin24.bankapi.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.senin24.bankapi.api.domain.Customer;
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

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@WebMvcTest(BankApiRestController.class)
public class BankApiRestControllerTest {

    private static final Long TEST_CUSTOMER_ID_01 = 1L;
    private static final Long TEST_CUSTOMER_ID_02 = 2L;
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private AccountService accountService;
    @MockBean
    private TransactService transactService;


//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }

    @Test
    public void getCustomersSuccess() throws Exception {
        String content = "[{\"id\":1,\"name\":\"Клиент01\",\"inn\":6201234567891,\"description\":\"тестовый клиент\"}," +
                "{\"id\":2,\"name\":\"Клиент02\",\"inn\":6201234567892,\"description\":\"тестовый клиент\"}]";

        Customer customer01 = new Customer("Клиент01", 6201234567891L, "тестовый клиент");
        customer01.setId(1L);
        Customer customer02 = new Customer("Клиент02", 6201234567892L, "тестовый клиент");
        customer02.setId(2L);

        given(this.customerService.findAllCustomers()).willReturn(
                Lists.newArrayList(customer01, customer02));

        this.mockMvc.perform(get("/v1/customers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(content))
                .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @Test
    public void getCustomerByIdSuccess() throws Exception {
        String content = "{\"id\":" + TEST_CUSTOMER_ID_02 + ",\"name\":\"Клиент02\",\"inn\":6201234567892,\"description\":\"тестовый клиент\"}";

        Customer customer02 = new Customer("Клиент02", 6201234567892L, "тестовый клиент");
        customer02.setId(TEST_CUSTOMER_ID_02);

        given(this.customerService.findById(TEST_CUSTOMER_ID_02)).willReturn(Optional.ofNullable(customer02));

        this.mockMvc.perform(get("/v1/customers/{customer_id}", TEST_CUSTOMER_ID_02).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(content));


    }

//    @Test
//    public void getAccountsByCustomerId() {
//    }
//
//    @Test
//    public void getAccountById() {
//    }
//
//    @Test
//    public void getTransactionsByAccountId() {
//    }
//
//    @Test
//    public void getTransactById() {
//    }

    @Test
    public void createCustomerSuccess() throws Exception {
        Customer customer01 = new Customer("Клиент01", 6201234567891L);
        String json = mapper.writeValueAsString(customer01);
        customer01.setId(TEST_CUSTOMER_ID_01);

        given(this.customerService.create(new Customer("Клиент01", 6201234567891L))).willReturn(customer01);

        mockMvc.perform(post("/v1/customers").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/v1/customers/" + TEST_CUSTOMER_ID_01)));
    }

//    @Test
//    public void createAccount() {
//    }
//
//    @Test
//    public void createAndRunTransact() {

//    }

    @Test
    public void updateCustomer() throws Exception {
        RB rb = new RB();
        rb.setName("Client01-update");
        rb.setDescription("Description-update");
        String json = mapper.writeValueAsString(rb);
//        given(this.customerService.update(rb.getName(), rb.getDescription(), TEST_CUSTOMER_ID_01))
        given(this.customerService.update("", "", 0L))
                .willReturn(new Customer(TEST_CUSTOMER_ID_01, rb.getName(), rb.getDescription(), 6201234567891L ));
        System.out.println(json);
        this.mockMvc.perform(put("/v1/customers/" + TEST_CUSTOMER_ID_01).contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/v1/customers/" + TEST_CUSTOMER_ID_01)));
    }

//    @Test
//    public void updateAccount() {
//    }
//
//    @Test
//    public void updateTransaction() {
//    }
}