package com.javabank.the_java_bank.utils;

import java.time.Year;

public class AccountUtils {
	public static final String ACCOUNT_EXISTS_CODE = "001";
	public static final String ACCOUNT_EXISTS_MESSAGE = "This user already has an account Created..!";
	
	public static final String ACCOUNT_CREATION_SUCCESS_CODE = "002";
	public static final String ACCOUNT_CREATION_MESSAGE = "Account has been successfully created..!";
	
	public static final String ACCOUNT_NOT_EXIST_CODE = "003";
	public static final String ACCOUNT_NOT_EXIST_MESSAGE = "User with provided Account Number does not exist";
	
	public static final String ACCOUNT_FOUND_CODE = "004";
	public static final String ACCOUNT_FOUND_SUCCESS = "USER ACCOUNT FOUND";
	
	public static final String ACCOUNT_CREDITED_SUCCESS_CODE = "005";
	public static final String ACCOUNT_CREDITED_SUCCESS_MESSAGE = "User Account Credited Successfully";
	
	public static final String ACCOUNT_DEBITED_SUCCESS_CODE = "006";
	public static final String ACCOUNT_DEBITED_SUCCESS_MESSAGE = "User Account Debited Successfully";
	
	public static final String ACCOUNT_INSUFFICIENT_CODE = "007";
	public static final String ACCOUNT_INSUFFICIENT_MESSAGE = "Insufficient balance";
	
	public static final String DEBIT_ACCOUNT_NOT_EXIST_CODE = "This account ";
	
	public static final String TRANSFER_SUCCESSFUL_CODE = "008";
	public static final String TRANSFER_SUCCESSFUL_MESSAGE = "Transfer Successful.";
	
	public static String generanteAccountNumber()
	{
		/* 2026 + random six digits */
		
		Year currentYear= Year.now();
		int min = 100000;
		int max = 999999;
		
//		genereate a random number between min and max number;
		
		int randNumber = (int) Math.floor(Math.random() * (max-min + 1) + min ) ;
		
		// convert the current year and randomnumbet to string then concatenate
		
		String year = String.valueOf(currentYear);
		String num = String.valueOf(randNumber);
//		StringBuilder accountNumber = new StringBuilder();
		
		return year + num;
//		return accountNumber.append(year).append(num);
	}

}
