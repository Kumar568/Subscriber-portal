package com.emudhra.emra.subscriber.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(GlobalExceptionHandler.class);

	
	@ExceptionHandler(CommonException.class)
	public ResponseEntity<String> handleExpception(CommonException exception){
		LOGGER.error(exception.getErrorCode()+"::"+exception.getErrorMessage(), exception);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getErrorCode()+"::"+exception.getErrorMessage());

	}

}
