package com.javabank.the_java_bank.dto;

public class EmailDetails {

	private String recipient;
	private String messageBody;
	private String subject;
	private String attachment;
	
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public EmailDetails(String recipient, String messageBody, String subject, String attachment) {
		super();
		this.recipient = recipient;
		this.messageBody = messageBody;
		this.subject = subject;
		this.attachment = attachment;
	}
	public EmailDetails() {
		super();
	}
	@Override
	public String toString() {
		return "EmailDetails [recipient=" + recipient + ", messageBody=" + messageBody + ", subject=" + subject
				+ ", attachment=" + attachment + "]";
	}
	
	
	
}
