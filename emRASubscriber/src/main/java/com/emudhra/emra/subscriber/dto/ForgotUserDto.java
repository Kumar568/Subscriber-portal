package com.emudhra.emra.subscriber.dto;

public class ForgotUserDto {
	
	private String emailid;
	private String username;
	private Long userId;
	private String password;
	private String currentpassword;
	private String subscribecaptcha;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCurrentpassword() {
		return currentpassword;
	}
	public void setCurrentpassword(String currentpassword) {
		this.currentpassword = currentpassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getSubscribecaptcha() {
		return subscribecaptcha;
	}
	public void setSubscribecaptcha(String subscribecaptcha) {
		this.subscribecaptcha = subscribecaptcha;
	}
	

}
