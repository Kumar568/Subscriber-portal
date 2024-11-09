package com.emudhra.emra.subscriber.dto;

import java.time.LocalDateTime;

public class EmailVerificationDto {
	private int createdby;
	private int isVerified;
	private int isMailSent;
	private String encryptedOtp;
	private LocalDateTime emailSentDate;
	private String emailVerificationToken;
	private LocalDateTime emailVerificationTokenCreatedDate;
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public int getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}
	public int getIsMailSent() {
		return isMailSent;
	}
	public void setIsMailSent(int isMailSent) {
		this.isMailSent = isMailSent;
	}
	public String getEncryptedOtp() {
		return encryptedOtp;
	}
	public void setEncryptedOtp(String encryptedOtp) {
		this.encryptedOtp = encryptedOtp;
	}
	public LocalDateTime getEmailSentDate() {
		return emailSentDate;
	}
	public void setEmailSentDate(LocalDateTime emailSentDate) {
		this.emailSentDate = emailSentDate;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public LocalDateTime getEmailVerificationTokenCreatedDate() {
		return emailVerificationTokenCreatedDate;
	}
	public void setEmailVerificationTokenCreatedDate(LocalDateTime emailVerificationTokenCreatedDate) {
		this.emailVerificationTokenCreatedDate = emailVerificationTokenCreatedDate;
	}
	
	
	
}
