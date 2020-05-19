package com.assignment.transaction.controller;

import com.assignment.transaction.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllTransactions() throws Exception {
        mockMvc.perform(get("/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(7)));
    }

    @Test
    public void testGetTransactionsByTransactionAccount() throws Exception {
        mockMvc.perform(get("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .param("transactionAccount", "SAVINGS"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].transactionAccount").value("SAVINGS"))
                .andExpect(jsonPath("$[*].transactionId").value(Matchers.containsInAnyOrder(1001, 1002, 1003)));
    }

    @Test
    public void testGetTransactionsByTransactionDescription() throws Exception {
        mockMvc.perform(get("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .param("transactionDescription", "credit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[*].transactionId").value(Matchers.containsInAnyOrder(1003, 1006, 1007)));
    }

    @Test
    public void testGetTransactionsByTransactionAccountAndDescription() throws Exception {
        mockMvc.perform(get("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .param("transactionAccount", "SAVINGS")
                .param("transactionDescription", "credit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].transactionId").value(1003));
    }
}
