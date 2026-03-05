package com.javabank.the_java_bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javabank.the_java_bank.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>{

}
