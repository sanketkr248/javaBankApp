package com.javabank.the_java_bank.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String transactionId;
	private String transactionType;
	private BigDecimal amount;
	private String accountNumber;
	private String status;
	
	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime modifiedAt;
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionType=" + transactionType + ", amount="
				+ amount + ", accountNumber=" + accountNumber + ", status=" + status + "]";
	}
	public Transaction(String transactionId, String transactionType, BigDecimal amount, String accountNumber,
			String status) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.accountNumber = accountNumber;
		this.status = status;
	}
	public Transaction() {
		super();
	}
	
	
}
