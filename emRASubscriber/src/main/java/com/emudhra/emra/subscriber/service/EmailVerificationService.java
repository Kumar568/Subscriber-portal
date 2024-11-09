package com.emudhra.emra.subscriber.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.emudhra.emra.subscriber.entity.entity.Application;
import com.emudhra.emra.subscriber.entity.entity.EmailVerification;
import com.emudhra.emra.subscriber.mapper.EmailVerificationMapper;
import com.emudhra.emra.subscriber.repository.EmailVerificationRepository;

@Service
public class EmailVerificationService {

	@Autowired
	private EmailVerificationRepository emailVerificationRepository;
	
	@Autowired
	private EmailVerificationMapper emailVerificationMapper;
	
	 public EmailVerification saveEmailVerification(EmailVerification mailVerification) {
			return emailVerificationRepository.save(mailVerification);
		}
	
	 public EmailVerification getEmailVerificationdata(Long applicationId, 
			 int verificationMode) {
		 
		 Application application = new Application();
		 application.setId(applicationId);
		 EmailVerification emailVerification = emailVerificationRepository.findByApplicationAndChecklistModeId(application,verificationMode);
		 return emailVerification;
	 }
	 
	 
	 public Long decodeBase64ToLong(String encodedValue) {
	        try {
	            Base64.Decoder decoder = Base64.getDecoder();
	            byte[] decodedBytes = decoder.decode(encodedValue);
	            String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);
	            return Long.parseLong(decodedString);
	        } catch (NumberFormatException e) {
	            // Handle the case where the decoded string is not a valid Long
	            throw new IllegalArgumentException("Invalid encoded value for Long", e);
	        }
	 }
	 
	 public Boolean isExistApplicationId(Long applicationId, 
			 int verificationMode) {
		 
		 Application application = new Application();
		 application.setId(applicationId);
		 EmailVerification emailVerification = emailVerificationRepository.findByApplicationAndChecklistModeId(application,verificationMode);
		 return emailVerification !=null;
	 }
	 
	 
}
