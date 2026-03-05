package com.javabank.the_java_bank.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfo {
	
	@Schema(
			name = "User Account Name"
	)
	private String accountName;
	@Schema(
			name = "User Account Balance"
	)
	private BigDecimal accountBalance;
	
	@Schema(
			name = "User Account Number"
	)
	private String accountNumber;
	
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public AccountInfo(String accountName, BigDecimal accountBalance, String accountNumber) {
		super();
		this.accountName = accountName;
		this.accountBalance = accountBalance;
		this.accountNumber = accountNumber;
	}
	public AccountInfo() {
		super();
	}
	@Override
	public String toString() {
		return "AccountInfo [accountName=" + accountName + ", accountBalance=" + accountBalance + ", accountNumber="
				+ accountNumber + "]";
	}
	
	
	
}
