package com.javabank.the_java_bank.service;

import java.io.File;
import java.util.Objects;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.javabank.the_java_bank.dto.EmailDetails;
import com.javabank.the_java_bank.service.impl.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String senderEmail;
	
	private static final Logger log =
            LoggerFactory.getLogger(BankStatement.class);
	@Override
	public void sendEmailAlert(EmailDetails emailDetails) {
		// TODO Auto-generated method stub
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(senderEmail);
			mailMessage.setTo(emailDetails.getRecipient());
			mailMessage.setText(emailDetails.getMessageBody());
			mailMessage.setSubject(emailDetails.getSubject());
			
			javaMailSender.send(mailMessage);
			System.out.println("Mail send successfully!!");
			
		} catch (MailException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void sendEmailWithAttachment(EmailDetails emailDetails) {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(senderEmail);
			mimeMessageHelper.setTo(emailDetails.getRecipient());
			mimeMessageHelper.setText(emailDetails.getMessageBody());
			mimeMessageHelper.setSubject(emailDetails.getSubject());
			FileSystemResource file = new FileSystemResource(new File(emailDetails.getAttachment()));
			mimeMessageHelper.addAttachment(file.getFilename(), file);
			javaMailSender.send(mimeMessage);
			log.info(file.getFilename() + " has been send to user with email " + emailDetails.getRecipient());
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(senderEmail);
		mailMessage.setTo(emailDetails.getRecipient());
		mailMessage.setText(emailDetails.getMessageBody());
		mailMessage.setSubject(emailDetails.getSubject());
	}
	
}
