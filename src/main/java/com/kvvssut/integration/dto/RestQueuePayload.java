package com.kvvssut.integration.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class RestQueuePayload implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private BigDecimal amount;
	private int retryCounter;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRetryCounter() {
		return retryCounter;
	}

	public void setRetryCounter(int retryCounter) {
		this.retryCounter = retryCounter;
	}

	@Override
	public String toString() {
		return "RestQueuePayload [userName=" + userName + ", amount=" + amount
		          + ", retryCounter=" + retryCounter + "]";
	}

}
