package com.javabank.the_java_bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javabank.the_java_bank.entity.Transaction;
import com.javabank.the_java_bank.service.BankStatement;

@RestController
@RequestMapping("/bankStatement")
public class TransactionController {

	public TransactionController() {
		super();
	}
	
	@Autowired
	private BankStatement bankStatement;
	
	
	@GetMapping
	public List<Transaction> generateBankStatement(@RequestParam String accountNumber, @RequestParam String startDate, @RequestParam String endDate) throws Exception
	{
		 try {
		        return bankStatement.generateStatement(accountNumber, startDate, endDate);
		    } catch (Exception e) {
		        throw new RuntimeException("Error generating bank statement: " + e.getMessage());
		    }
	}

	
}
