package com.javabank.the_java_bank.service.impl;

import com.javabank.the_java_bank.dto.EmailDetails;

public interface EmailService {

	void sendEmailAlert(EmailDetails emailDetails);
	void sendEmailWithAttachment(EmailDetails emailDetails);
}
