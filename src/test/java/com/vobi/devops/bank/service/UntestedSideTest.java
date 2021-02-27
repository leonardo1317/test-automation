package com.vobi.devops.bank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UntestedSideTest {

	@InjectMocks
	@Spy
	UntestedSide untestedSide;
	
	@Test
	void shouldFailWhenGivenFalse() {
		// Arrange
		boolean input = false;
		String result;
		String expected = "FAIL";
		
		// Act
		result = untestedSide.foo(input);

		// Assert
		
		assertEquals(expected, result);
	}

	@Test
	void shouldBeOkWhenGivenTrue() {
		// Arrange
		boolean input = true;
		String result;
		String expected = "OK";
		
		//No quiero que haga nada, cuando invoquen a untestedSide.performAction
		//NO significa, que la linea NO pueda estar (Invocación)
		//Por que si NO está, la prueba falla
		doNothing().when(untestedSide).performAction();
		
		// Act
		result = untestedSide.foo(input);
		
		// Assert
		//Quiero verificar que efectivamente se haya llamado (1 y solo 1 vez)
		verify(untestedSide).performAction();
		assertEquals(expected, result);
	}

}
