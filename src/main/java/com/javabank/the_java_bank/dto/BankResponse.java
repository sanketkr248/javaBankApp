package com.javabank.the_java_bank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankResponse {

	private String responseCode;
	private String  responseMessage;
	private AccountInfo accountInfo;
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public AccountInfo getAccountInfo() {
		return accountInfo;
	}
	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}
	public BankResponse(String responseCode, String responseMessage, AccountInfo accountInfo) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.accountInfo = accountInfo;
	}
	public BankResponse() {
		super();
	}
	@Override
	public String toString() {
		return "BankResponse [responseCode=" + responseCode + ", responseMessage=" + responseMessage + ", accountInfo="
				+ accountInfo + "]";
	}
	
	
}
