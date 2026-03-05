package com.javabank.the_java_bank.dto;

import java.math.BigDecimal;

public class TransactionalDto {
	private String transactionType;
	private BigDecimal amount;
	private String accountNumber;
	private String status;
	@Override
	public String toString() {
		return "TransactionalDto [transactionType=" + transactionType + ", amount=" + amount + ", accountNumber="
				+ accountNumber + ", status=" + status + "]";
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public TransactionalDto(String transactionType, BigDecimal amount, String accountNumber, String status) {
		super();
		this.transactionType = transactionType;
		this.amount = amount;
		this.accountNumber = accountNumber;
		this.status = status;
	}
	public TransactionalDto() {
		super();
	}
	
}
