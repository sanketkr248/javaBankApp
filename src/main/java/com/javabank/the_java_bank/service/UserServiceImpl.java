package com.javabank.the_java_bank.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javabank.the_java_bank.config.JwtTokenProvider;
import com.javabank.the_java_bank.dto.AccountInfo;
import com.javabank.the_java_bank.dto.BankResponse;
import com.javabank.the_java_bank.dto.CreditDebitRequest;
import com.javabank.the_java_bank.dto.EmailDetails;
import com.javabank.the_java_bank.dto.EnquiryRequest;
import com.javabank.the_java_bank.dto.LoginDto;
import com.javabank.the_java_bank.dto.TransactionalDto;
import com.javabank.the_java_bank.dto.TransferRequest;
import com.javabank.the_java_bank.dto.UserRequest;
import com.javabank.the_java_bank.entity.Role;
import com.javabank.the_java_bank.entity.User;
import com.javabank.the_java_bank.repository.UserRepository;
import com.javabank.the_java_bank.service.impl.EmailService;
import com.javabank.the_java_bank.service.impl.UserService;
import com.javabank.the_java_bank.utils.AccountUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	TransactionServiceImpl transactionServiceImpl;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Override
	public BankResponse createAccount(UserRequest userRequest) {
		// TODO Auto-generated method stub
		
		if(userRepository.existsByEmail(userRequest.getEmail()))
		{
			BankResponse response = new BankResponse();
			response.setResponseCode(AccountUtils.ACCOUNT_EXISTS_CODE);
			response.setResponseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE);
			response.setAccountInfo(null);
			return response;
		}
		User newUser = new User();
		newUser.setFirstName(userRequest.getFirstName());
		newUser.setLastName(userRequest.getLastName());
		newUser.setOtherName(userRequest.getOtherName());
		newUser.setGender(userRequest.getGender());
		newUser.setAddress(userRequest.getAddress());
		newUser.setStateOfOrigin(userRequest.getStateOfOrigin());
		newUser.setAccountNumber(AccountUtils.generanteAccountNumber());
		newUser.setAccountBalance(BigDecimal.ZERO);
		newUser.setEmail(userRequest.getEmail());
		newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		newUser.setPhoneNumber(userRequest.getPhoneNumber());
		newUser.setAlternativePhoneNumber(userRequest.getAlternativePhoneNumber());
		newUser.setStatus("ACTIVE");
		newUser.setRole(userRequest.getRole());
		User savedUser = userRepository.save(newUser);
		
		// send email alert
		EmailDetails emailDetails = new EmailDetails();
		emailDetails.setRecipient(savedUser.getEmail());
		emailDetails.setSubject("ACCOUNT CREATION");
		emailDetails.setMessageBody("Your Account has been successfully created..!\n Your Account Details: \n Account Name: "+ savedUser.getFirstName() + " "+ savedUser.getLastName() + " " + savedUser.getOtherName()+"\nAccount Number: "+savedUser.getAccountNumber());
		emailService.sendEmailAlert(emailDetails);
		
		
		
		BankResponse response = new BankResponse();
		response.setResponseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE);
		response.setResponseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE);
		AccountInfo info = new AccountInfo();
		info.setAccountBalance(savedUser.getAccountBalance());
		info.setAccountNumber(savedUser.getAccountNumber());
		info.setAccountName(savedUser.getFirstName()+ " " + savedUser.getLastName()+ " "+ savedUser.getOtherName());
		response.setAccountInfo(info);
		
		return response;
	}
	
//	public BankResponse login(LoginDto loginDto)
//	{
//		Authentication authentication = null;
//		authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
//		EmailDetails loginAlert = new EmailDetails();
//		loginAlert.setSubject("You are logged in.!!");
//		loginAlert.setRecipient(loginDto.getEmail());
//		loginAlert.setMessageBody("You logged into your account. If you did not initiated this request, please contact your bank.");
//
//		emailService.sendEmailAlert(loginAlert);
//		BankResponse response  = new BankResponse();
//		response.setResponseCode("Login Success!");
//		response.setResponseMessage(jwtTokenProvider.generateToken(authentication));
//		return response;
//	}
	public BankResponse login(LoginDto loginDto) {

	    BankResponse response = new BankResponse();

	    try {
	        Authentication authentication =
	                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( loginDto.getEmail(), loginDto.getPassword()));
	        EmailDetails loginAlert = new EmailDetails();
	        loginAlert.setSubject("You are logged in.!!");
	        loginAlert.setRecipient(loginDto.getEmail());
	        loginAlert.setMessageBody(
	                "You logged into your account. If you did not initiate this request, please contact your bank.");

	        emailService.sendEmailAlert(loginAlert);

	        response.setResponseCode("Login Success!");
	        response.setResponseMessage(jwtTokenProvider.generateToken(authentication));
	        return response;

	    } catch (Exception e) {
	        response.setResponseCode("Login Failed");
	        response.setResponseMessage("Invalid email or password");
	        return response;
	    }
	}
	

	@Override
	public BankResponse balanceEnquiry(EnquiryRequest request) {
		boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
		if(!isAccountExist)
		{
			BankResponse response = new BankResponse();
			response.setResponseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE);
			response.setResponseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE);
			response.setAccountInfo(null);
			return response;
		}
		User foundUser = userRepository.findByAccountNumber(request.getAccountNumber());
		BankResponse res = new BankResponse();
		res.setResponseCode(AccountUtils.ACCOUNT_FOUND_CODE);
		res.setResponseMessage(AccountUtils.ACCOUNT_FOUND_SUCCESS);
		AccountInfo info = new AccountInfo();
		info.setAccountBalance(foundUser.getAccountBalance());
		info.setAccountNumber(foundUser.getAccountNumber());
		info.setAccountName(foundUser.getFirstName() + " "+ foundUser.getLastName());
		res.setAccountInfo(info);
		return res;
	}

	@Override
	public String nameEnquiry(EnquiryRequest request) {
		
		boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
		if(!isAccountExist)
			return AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE;
		
		User foundUser = userRepository.findByAccountNumber(request.getAccountNumber());
		return foundUser.getFirstName() + " "+ foundUser.getLastName() ;
	}

	@Override
	public BankResponse creditAccount(CreditDebitRequest request) {
		//check if the4 account exists
		boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
		
		if(!isAccountExist)
		{
			BankResponse response = new BankResponse();
			response.setResponseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE);
			response.setResponseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE);
			response.setAccountInfo(null);
			return response;
		}
		
		
		User foundUser = userRepository.findByAccountNumber(request.getAccountNumber());
		foundUser.setAccountBalance(foundUser.getAccountBalance().add(request.getAmount()));
		userRepository.save(foundUser);
		
		// save transaction 
		TransactionalDto transactionalDto = new TransactionalDto();
		transactionalDto.setAccountNumber(foundUser.getAccountNumber());
		transactionalDto.setTransactionType("CREDIT");
		transactionalDto.setAmount(request.getAmount());
		transactionServiceImpl.saveTransaction(transactionalDto);
		
		BankResponse res = new BankResponse();
		res.setResponseCode(AccountUtils.ACCOUNT_CREDITED_SUCCESS_CODE);
		res.setResponseMessage(AccountUtils.ACCOUNT_CREDITED_SUCCESS_MESSAGE);
		AccountInfo info = new AccountInfo();
		info.setAccountName(foundUser.getFirstName() + " " + foundUser.getLastName());
		info.setAccountBalance(foundUser.getAccountBalance());
		info.setAccountNumber(foundUser.getAccountNumber());
		res.setAccountInfo(info);
		return res;
	}

	@Override
	public BankResponse debitAccount(CreditDebitRequest request) {
		
		boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
		
		if(!isAccountExist)
		{
			BankResponse response = new BankResponse();
			response.setResponseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE);
			response.setResponseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE);
			response.setAccountInfo(null);
			return response;
		}
		User foundUser = userRepository.findByAccountNumber(request.getAccountNumber());
		
		if(foundUser.getAccountBalance().compareTo(request.getAmount()) < 0)
		{
			BankResponse response = new BankResponse();
			response.setResponseCode(AccountUtils.ACCOUNT_INSUFFICIENT_CODE);
			response.setResponseMessage(AccountUtils.ACCOUNT_INSUFFICIENT_MESSAGE);
			response.setAccountInfo(null);
			return response;
		}
		
		else
		{
			foundUser.setAccountBalance(foundUser.getAccountBalance().subtract(request.getAmount()));
			userRepository.save(foundUser);
			
			TransactionalDto transactionalDto = new TransactionalDto();
			transactionalDto.setAccountNumber(foundUser.getAccountNumber());
			transactionalDto.setTransactionType("DEBIT");
			transactionalDto.setAmount(request.getAmount());
			transactionServiceImpl.saveTransaction(transactionalDto);
			
			BankResponse res = new BankResponse();
			res.setResponseCode(AccountUtils.ACCOUNT_DEBITED_SUCCESS_CODE);
			res.setResponseMessage(AccountUtils.ACCOUNT_DEBITED_SUCCESS_MESSAGE);
			AccountInfo info = new AccountInfo();
			info.setAccountName(foundUser.getFirstName() + " " + foundUser.getLastName());
			info.setAccountBalance(foundUser.getAccountBalance());
			info.setAccountNumber(foundUser.getAccountNumber());
			res.setAccountInfo(info);
			return res;
		}
	}

	@Override
	public BankResponse transfer(TransferRequest request) 
	{
		// get the account to debit ( check if it exists)
		// check if the amount i am debitting is not more than the current balance
		// debit the amout
		// get the amount to credit
		// credit the account
		
		boolean isDestinationAccountExist = userRepository.existsByAccountNumber(request.getDestinationAccountNumber());
		if(!isDestinationAccountExist)
		{
			BankResponse response = new BankResponse();
			response.setResponseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE);
			response.setResponseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE);
			response.setAccountInfo(null);
			return response;
		}
		
		User sourceAccountUser = userRepository.findByAccountNumber(request.getSourceAccountNumber());
		
		if(request.getAmount().compareTo(sourceAccountUser.getAccountBalance()) > 0)
		{
			BankResponse response = new BankResponse();
			response.setResponseCode(AccountUtils.ACCOUNT_INSUFFICIENT_CODE);
			response.setResponseMessage(AccountUtils.ACCOUNT_INSUFFICIENT_MESSAGE);
			response.setAccountInfo(null);
			return response;
		}
		
		sourceAccountUser.setAccountBalance(sourceAccountUser.getAccountBalance().subtract(request.getAmount()));
		String sourceAccountHolderName = sourceAccountUser.getFirstName() + " " +sourceAccountUser.getLastName();
		userRepository.save(sourceAccountUser);
		
		EmailDetails debitAlert = new EmailDetails();
		debitAlert.setSubject("DEBIT ALERT !!");
		debitAlert.setRecipient(sourceAccountUser.getEmail());
		debitAlert.setMessageBody("The sum of "+ request.getAmount() + " has been deducted from your current balance is "+sourceAccountUser.getAccountBalance());
		emailService.sendEmailAlert(debitAlert);
		TransactionalDto transactionalDto1 = new TransactionalDto();
		transactionalDto1.setAccountNumber(sourceAccountUser.getAccountNumber());
		transactionalDto1.setTransactionType("DEBIT");
		transactionalDto1.setAmount(request.getAmount());
		transactionServiceImpl.saveTransaction(transactionalDto1);
		
		User destinationAccountUser = userRepository.findByAccountNumber(request.getDestinationAccountNumber());
		destinationAccountUser.setAccountBalance(destinationAccountUser.getAccountBalance().add(request.getAmount()));
//		String recipientUserName = destinationAccountUser.getFirstName() + " " + destinationAccountUser.getLastName();
		userRepository.save(destinationAccountUser);
		
		EmailDetails creditAlert = new EmailDetails();
		creditAlert.setSubject("CREDIT ALERT !!");
		creditAlert.setRecipient(destinationAccountUser.getEmail());
		creditAlert.setMessageBody("The sum of "+ request.getAmount() + " has been credited to your account from "+ sourceAccountHolderName + " Your current balance is " + destinationAccountUser.getAccountBalance());
		emailService.sendEmailAlert(creditAlert);
		
		TransactionalDto transactionalDto = new TransactionalDto();
		transactionalDto.setAccountNumber(destinationAccountUser.getAccountNumber());
		transactionalDto.setTransactionType("CREDIT");
		transactionalDto.setAmount(request.getAmount());
		transactionServiceImpl.saveTransaction(transactionalDto);
		
		BankResponse response = new BankResponse();
		response.setResponseCode(AccountUtils.TRANSFER_SUCCESSFUL_CODE);
		response.setResponseMessage(AccountUtils.TRANSFER_SUCCESSFUL_MESSAGE);
		response.setAccountInfo(null);
		return response;
	
	}
	
	public UserServiceImpl(UserRepository userRepository, EmailService emailService,
			TransactionServiceImpl transactionServiceImpl, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.emailService = emailService;
		this.transactionServiceImpl = transactionServiceImpl;
		this.passwordEncoder = passwordEncoder;
	}
	

	public UserServiceImpl() {
		super();
	}

//	public static void main(String args[])
//	{
//		UserServiceImpl userSerImpl = new UserServiceImpl();
//		System.out.println(userSerImpl.passwordEncoder.encode("1234"));
//	}
}
