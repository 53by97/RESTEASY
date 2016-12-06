package com.kvvssut.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RestDTO {

	private long id;
	private String userName;
	private BigDecimal totalBalance;
	private Date createdAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "RestDTO [id=" + id + ", userName=" + userName + ", totalBalance=" + totalBalance
		          + ", createdAt=" + createdAt + "]";
	}

}
