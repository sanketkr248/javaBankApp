package com.javabank.the_java_bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javabank.the_java_bank.dto.TransactionalDto;
import com.javabank.the_java_bank.entity.Transaction;
import com.javabank.the_java_bank.repository.TransactionRepository;
import com.javabank.the_java_bank.service.impl.TransactionService;

@Component
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository repository;
	@Override
	public void saveTransaction(TransactionalDto transactionalDto) {
		Transaction transaction = new Transaction();
		transaction.setTransactionType(transactionalDto.getTransactionType());
		transaction.setAccountNumber(transactionalDto.getAccountNumber());
		transaction.setAmount(transactionalDto.getAmount());
		transaction.setStatus("SUCCESS");
		repository.save(transaction);
		System.out.println("Transaction Saved Successfully..!");
	}

}
