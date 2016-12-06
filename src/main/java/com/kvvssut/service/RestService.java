package com.kvvssut.service;

import java.math.BigDecimal;

import com.kvvssut.dto.RestRequestDTO;

public interface RestService {

	public String checkDetails(String userName, RestRequestDTO restRequestDTO);

	public void verifyUserDetails(String userName, BigDecimal bigDecimal);

	public BigDecimal getBalance(String userName);

}
