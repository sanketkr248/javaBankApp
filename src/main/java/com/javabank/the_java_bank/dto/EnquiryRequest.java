package com.javabank.the_java_bank.dto;

public class EnquiryRequest {
	
	private String accountNumber;

	@Override
	public String toString() {
		return "EnquiryRequest [accountNumber=" + accountNumber + "]";
	}

	public EnquiryRequest(String accountNumber) {
		super();
		this.accountNumber = accountNumber;
	}

	public EnquiryRequest() {
		super();
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	

}
