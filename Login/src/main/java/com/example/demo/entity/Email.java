package com.example.demo.entity;


public class Email {
    private String recipientEmail;
    private String subject;
    private String content;
    private Boolean emailSent;
    
	
	public Email() {
	}


	public String getRecipientEmail() {
		return recipientEmail;
	}


	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Boolean isEmailSent() {
		return emailSent;
	}


	public void setEmailSent(Boolean emailSent) {
		this.emailSent = emailSent;
	}


	public Email(String recipientEmail, String subject, String content, Boolean emailSent) {
		super();
		this.recipientEmail = recipientEmail;
		this.subject = subject;
		this.content = content;
		this.emailSent = emailSent;
	}
	
    }
