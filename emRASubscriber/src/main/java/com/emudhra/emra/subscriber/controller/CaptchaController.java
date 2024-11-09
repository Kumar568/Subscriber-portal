package com.emudhra.emra.subscriber.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emudhra.emra.subscriber.business.CaptchaGenerator;
import com.emudhra.emra.subscriber.dto.Captcha;
import com.emudhra.emra.subscriber.enums.ErrorCodes;
import com.emudhra.emra.subscriber.exception.CommonException;

@RestController
public class CaptchaController {
	@Autowired
	private CaptchaGenerator captchaGenerator;
	

	
	@GetMapping("/getcaptcha")
	public Captcha getcaptcha(HttpSession session) {
	    try {
	        Captcha captchaNumber = captchaGenerator.generateCaptcha();
	        session.setAttribute("expectedCaptcha", captchaNumber);
	        return captchaNumber;
	    } catch (Exception e) {
	        // Handle the exception
	        e.printStackTrace(); // 
	        
	        // Throw a custom exception with error code and description
	        throw new CommonException(ErrorCodes.SUBLOGIN001.getCode(), ErrorCodes.SUBLOGIN001.getDescription(), e);
	    }
	}


}
