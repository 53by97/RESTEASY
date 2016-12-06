package com.kvvssut.controller;

import java.math.BigDecimal;

public interface RestController {

	public abstract void updateBalanceByUserName(String userName, BigDecimal amount);

	public abstract BigDecimal getBalanceByUserName(String userName);

}
