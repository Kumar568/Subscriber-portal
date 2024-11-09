package com.emudhra.emra.subscriber.exception;

public class CommonException extends RuntimeException{

	private static final long serialVersionUID = 6968590927072771548L;
	private String errorCode;
	private String errorMessage;
	
	

	public CommonException(String errorCode, String errorMessage, Exception e) {
		super();
		this.errorCode = errorCode;
		this.errorMessage=errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
	

}
