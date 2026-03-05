package com.javabank.the_java_bank.service.impl;

import com.javabank.the_java_bank.dto.BankResponse;
import com.javabank.the_java_bank.dto.CreditDebitRequest;
import com.javabank.the_java_bank.dto.EnquiryRequest;
import com.javabank.the_java_bank.dto.LoginDto;
import com.javabank.the_java_bank.dto.TransferRequest;
import com.javabank.the_java_bank.dto.UserRequest;

public interface UserService {

	BankResponse createAccount(UserRequest userRequest);
	
	 BankResponse balanceEnquiry(EnquiryRequest request);
	 
	 String nameEnquiry(EnquiryRequest request);
	 
	 BankResponse creditAccount(CreditDebitRequest request);
	 
	 BankResponse debitAccount(CreditDebitRequest request);
	 
	 BankResponse transfer(TransferRequest request);
	 
	 BankResponse login(LoginDto loginDto);
}
