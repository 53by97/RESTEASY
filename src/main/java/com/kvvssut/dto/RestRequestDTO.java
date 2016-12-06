package com.kvvssut.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.kvvssut.validation.RequestResourceValidation;
import com.kvvssut.validation.constraint.ValidAmount;

public class RestRequestDTO implements RequestResourceValidation {

	@NotNull
	@ValidAmount
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "RestRequestDTO [amount=" + amount + "]";
	}

}
