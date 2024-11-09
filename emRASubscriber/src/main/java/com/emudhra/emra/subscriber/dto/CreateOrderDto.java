package com.emudhra.emra.subscriber.dto;

import java.time.LocalDateTime;



public class CreateOrderDto {
	private String userName;
	private String emailId;
	private String isActive;
	private LocalDateTime createdDate;
	private Long createdBy;
	private Long eraOrgId;
	private int originator;
	private String passwordChanged;
	private String password;
	private String captcha;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getEraOrgId() {
		return eraOrgId;
	}
	public void setEraOrgId(Long eraOrgId) {
		this.eraOrgId = eraOrgId;
	}
	public int getOriginator() {
		return originator;
	}
	public void setOriginator(int originator) {
		this.originator = originator;
	}
	public String getPasswordChanged() {
		return passwordChanged;
	}
	public void setPasswordChanged(String passwordChanged) {
		this.passwordChanged = passwordChanged;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	

}
