package com.javabank.the_java_bank.service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.javabank.the_java_bank.dto.EmailDetails;
import com.javabank.the_java_bank.entity.Transaction;
import com.javabank.the_java_bank.entity.User;
import com.javabank.the_java_bank.repository.TransactionRepository;
import com.javabank.the_java_bank.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BankStatement {
	
	/** 
	 * retrive list of trasactions within a date 
	 * 
	 * 
	 * 
	 * 
	 * **/
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	private static final String FILE = "E:\\Spring-Boot\\FILE\\STATEMENT.pdf";
	private static final Logger log =
	            LoggerFactory.getLogger(BankStatement.class);
	public BankStatement(TransactionRepository transactionRepository) {
		super();
		this.transactionRepository = transactionRepository;
	}
	
//	public List<Transaction> generateStatement(String accountNumber, String startDate, String endDate)
//	{
//		LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
//		LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
//
//		List<Transaction> transactionList = transactionRepository.findAll().stream().filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
//				.filter(transaction -> transaction.getCreatedAt().isEqual(start)).filter(transaction -> transaction.getCreatedAt().isEqual(end)).toList();
//		
//		return transactionList;
//	}
//	public List<Transaction> generateStatement(String accountNumber, String startDate, String endDate)
//	{
//	    LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
//	    LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
//
//	    List<Transaction> transactionList = transactionRepository.findAll()
//	            .stream()
//	            .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
//	            .filter(transaction -> 
//	                    !transaction.getCreatedAt().isBefore(start) &&
//	                    !transaction.getCreatedAt().isAfter(end)
//	            )
//	            .toList();
//
//	    return transactionList;
//	}
	public List<Transaction> generateStatement(String accountNumber, String startDate, String endDate) throws Exception {

	    LocalDate start = LocalDate.parse(startDate);
	    LocalDate end = LocalDate.parse(endDate);

	    List<Transaction> transactionList = transactionRepository.findAll()
	            .stream()
	            .filter(t -> t.getAccountNumber().equals(accountNumber))
	            .filter(t -> t.getCreatedAt() != null)
	            .filter(t ->
	                    !t.getCreatedAt().toLocalDate().isBefore(start) &&
	                    !t.getCreatedAt().toLocalDate().isAfter(end)
	            )
	            .toList();
	    User user = userRepository.findByAccountNumber(accountNumber);
	    String customerName = user.getFirstName() + " " + user.getLastName();
	    Rectangle statementSize = new Rectangle(PageSize.A4);
		Document document = new Document(statementSize);
		log.info("Setting size of document");
		
		OutputStream outputStream = new FileOutputStream(FILE);
		
		PdfWriter.getInstance(document, outputStream);
		document.open();
		
		PdfPTable bankInfoTable = new PdfPTable(1);
		PdfPCell bankName = new PdfPCell(new Phrase("The Java Bank"));
		bankName.setBorder(0);
		bankName.setBackgroundColor(BaseColor.BLUE);
		bankName.setPadding(20f);
		
		PdfPCell bankAddress = new PdfPCell(new Phrase("702 Pushpak Vashant Leela, Thane West, Maharashatra."));
		bankAddress.setBorder(0);
		
		bankInfoTable.addCell(bankName);
		bankInfoTable.addCell(bankAddress);
		
		PdfPTable statementInfo = new PdfPTable(2);
		PdfPCell customerInfo = new PdfPCell(new Phrase("Start Date: " + startDate));
		
		customerInfo.setBorder(0);
		PdfPCell statement = new PdfPCell(new Phrase("STATEMENT OF ACCOUNT"));
		statement.setBorder(0);
		PdfPCell stopDate = new PdfPCell(new Phrase("End Date"+ endDate));
		stopDate.setBorder(0);
		PdfPCell name = new PdfPCell(new Phrase("Customer Name: "+customerName));
		name.setBorder(0);
		
		PdfPCell space = new PdfPCell();
		PdfPCell address = new PdfPCell(new Phrase("Customer Address: "+ user.getAddress()));
		address.setBorder(0);
		
		PdfPTable transactionsTable = new PdfPTable(4);
		PdfPCell date = new PdfPCell(new Phrase("DATE"));
		date.setBackgroundColor(BaseColor.BLUE);
		date.setBorder(0);
		
		PdfPCell transactionType = new PdfPCell(new Phrase("Transaction Type"));
		transactionType.setBorder(0);
		transactionType.setBackgroundColor(BaseColor.BLUE);
		
		PdfPCell transactionAmount = new PdfPCell(new Phrase("Transaction Amount"));
		transactionAmount.setBorder(0);
		transactionAmount.setBackgroundColor(BaseColor.BLUE);
		
		PdfPCell status = new PdfPCell(new Phrase("STATUS"));
		status.setBackgroundColor(BaseColor.BLUE);
		status.setBorder(0);
		
		transactionsTable.addCell(date);
		transactionsTable.addCell(transactionType);
		transactionsTable.addCell(transactionAmount);
		transactionsTable.addCell(status);
		
		transactionList.forEach(transaction -> {
			transactionsTable.addCell(new Phrase(transaction.getCreatedAt().toString()));
			transactionsTable.addCell(new Phrase(transaction.getTransactionType()));
			transactionsTable.addCell(new Phrase(transaction.getAmount().toString()));
			transactionsTable.addCell(new Phrase(transaction.getStatus()));

		});
		statementInfo.addCell(customerInfo);
		statementInfo.addCell(statement);
		statementInfo.addCell(endDate);
		statementInfo.addCell(name);
		statementInfo.addCell(space);
		statementInfo.addCell(address);
		
		document.add(bankInfoTable);
		document.add(statementInfo);
		document.add(transactionsTable);
		
		document.close();
		
		EmailDetails emailDetails = new EmailDetails();
		emailDetails.setRecipient(user.getEmail());
		emailDetails.setSubject("STATEMENT OF ACCOUNT");
		emailDetails.setMessageBody("Kindly find your requested account statement attached!");
		emailDetails.setAttachment(FILE);
		
		emailServiceImpl.sendEmailWithAttachment(emailDetails);
		return transactionList;
	}
	

	

}
