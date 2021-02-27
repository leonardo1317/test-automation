package com.vobi.devops.bank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vobi.devops.bank.controller.BankTransactionController;
import com.vobi.devops.bank.dto.DepositDTO;
import com.vobi.devops.bank.dto.TransactionResultDTO;
import com.vobi.devops.bank.dto.TransferDTO;
import com.vobi.devops.bank.dto.WithdrawDTO;
import com.vobi.devops.bank.service.BankTransactionService;

@WebMvcTest(BankTransactionController.class)
@AutoConfigureMockMvc(addFilters = false) //Se salta los filtros de seguridad
class BankTransactionControllerTest {

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	BankTransactionService bankTransactionService;
	
	@Autowired
	MockMvc mockMvc;

	
	@Test
	void deposit_debe_retornar_un_200() throws Exception {
		//Arrange
		String accountId = "4640-0341-9387-5781";
		String userEmail = "vondrusek1@wisc.edu";
		Double amount = 15000.0;
		MvcResult mvcResult = null;
		
		DepositDTO depositDTO = new DepositDTO(accountId, amount, userEmail);
		String jsonDepositDTO = objectMapper.writeValueAsString(depositDTO);
		
		TransactionResultDTO transactionResultDTO = new TransactionResultDTO(32, 85000.0);
		
		//No me interesa probar la lógica de negocio
		when(bankTransactionService.deposit(any(DepositDTO.class))).
			thenReturn(transactionResultDTO);
		
		//Act / y parte de assert
		mvcResult = mockMvc
				.perform(post("/api/v1/transactions/deposit")
						.contentType("application/json")
						.content(jsonDepositDTO))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.balance").value(85000.0))
						.andReturn();
		
		//Assert
		assertEquals("application/json", mvcResult.getResponse().getContentType());
	}

}