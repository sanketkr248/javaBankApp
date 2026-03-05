package com.javabank.the_java_bank.dto;

import java.math.BigDecimal;

public class TransferRequest {
	
	private String sourceAccountNumber;
	private String destinationAccountNumber;
	private BigDecimal amount;
	public String getSourceAccountNumber() {
		return sourceAccountNumber;
	}
	public void setSourceAccountNumber(String sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}
	public String getDestinationAccountNumber() {
		return destinationAccountNumber;
	}
	public void setDestinationAccountNumber(String destinationAccountNumber) {
		this.destinationAccountNumber = destinationAccountNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public TransferRequest(String sourceAccountNumber, String destinationAccountNumber, BigDecimal amount) {
		super();
		this.sourceAccountNumber = sourceAccountNumber;
		this.destinationAccountNumber = destinationAccountNumber;
		this.amount = amount;
	}
	public TransferRequest() {
		super();
	}
	@Override
	public String toString() {
		return "TransferRequest [sourceAccountNumber=" + sourceAccountNumber + ", destinationAccountNumber="
				+ destinationAccountNumber + ", amount=" + amount + "]";
	}
	
	

}
