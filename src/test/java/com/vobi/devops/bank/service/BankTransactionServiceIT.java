package com.vobi.devops.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vobi.devops.bank.domain.Account;
import com.vobi.devops.bank.dto.DepositDTO;
import com.vobi.devops.bank.dto.TransactionResultDTO;

@SpringBootTest
class BankTransactionServiceIT {

	@Autowired
	BankTransactionService bankTransactionService;
	
	@Autowired
	AccountService accountService;
	
	@Test
	void deposit_debe_depositar_dinero() throws Exception {
		//Arrange
		
		String accountId = "4640-0341-9387-5781";
		Double amount = 15000d;
		String userEmail = "cfaier0@cafepress.com";
		TransactionResultDTO transactionResultDTO;
		
		DepositDTO depositDTO = new DepositDTO();
		depositDTO.setAccoId(accountId);
		depositDTO.setAmount(amount); 
		depositDTO.setUserEmail(userEmail);
		
		//Act
		
		//Consultar la cuenta para saber el saldo anterior
		Optional<Account> optCuentaConsultada = accountService.findById(accountId);
		if (!optCuentaConsultada.isPresent()) {
			fail("No existe la cuenta : " + accountId);
		}
		
		Account cuentaConsultada = optCuentaConsultada.get();
		Double saldoAnterior = cuentaConsultada.getBalance();
		
		//Realiza el depósito
		transactionResultDTO = bankTransactionService.deposit(depositDTO);
		
		//Assert
		assertNotNull(transactionResultDTO.getTransactionId());
		
		assertEquals(transactionResultDTO.getBalance(), saldoAnterior+amount);
		
	}

}