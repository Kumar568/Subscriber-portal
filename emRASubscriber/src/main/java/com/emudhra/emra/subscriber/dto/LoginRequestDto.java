package com.emudhra.emra.subscriber.dto;

public class LoginRequestDto {

	private String username;
	private String password;
	private String subscribecaptcha;

	public String getPassword() {
		return password;
	}

	public String getSubscribecaptcha() {
		return subscribecaptcha;
	}

	public void setSubscribecaptcha(String subscribecaptcha) {
		this.subscribecaptcha = subscribecaptcha;
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

}
