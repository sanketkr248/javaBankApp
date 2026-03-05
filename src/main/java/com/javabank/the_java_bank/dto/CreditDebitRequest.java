package com.javabank.the_java_bank.dto;

import java.math.BigDecimal;

public class CreditDebitRequest {

	private String accountNumber;
	private BigDecimal amount;
	@Override
	public String toString() {
		return "CreditDebitRequest [accountNumber=" + accountNumber + ", amount=" + amount + "]";
	}
	
	public CreditDebitRequest(String accountNumber, BigDecimal amount) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public CreditDebitRequest() {
		super();
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	
}
