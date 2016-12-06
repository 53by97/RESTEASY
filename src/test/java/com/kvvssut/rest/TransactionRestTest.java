package com.kvvssut.rest;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.kvvssut.dto.RestRequestDTO;

public class TransactionRestTest {

	@Inject
	private TransactionRest transactionRest;

	private RestRequestDTO dto;

	@Before
	public void setUp() throws Exception {
		dto = new RestRequestDTO();
		dto.setAmount(BigDecimal.valueOf(123.45));
	}

	@Test
	public void testMethod() {
		// transactionRest.method("username", dto);
	}

	@Test
	public void testGetBalanceByUsernameString() {
		// transactionRest.getBalanceByUsername("username");
	}

	@Test
	public void testGetBalanceByUsername() {
		// transactionRest.getBalanceByUsername();
	}

}
