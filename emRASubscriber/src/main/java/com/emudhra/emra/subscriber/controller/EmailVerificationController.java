package com.emudhra.emra.subscriber.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.emudhra.emra.subscriber.dto.EmailVerificationDto;
import com.emudhra.emra.subscriber.entity.entity.EmailVerification;
import com.emudhra.emra.subscriber.enums.ErrorCodes;
import com.emudhra.emra.subscriber.enums.Status;
import com.emudhra.emra.subscriber.enums.VerificationMode;
import com.emudhra.emra.subscriber.exception.CommonException;
import com.emudhra.emra.subscriber.service.EmailVerificationService;

@RestController
public class EmailVerificationController {
@Autowired
private EmailVerificationService emailVerificationService;
	
@GetMapping("/subscriber/emailverification")	
	public ModelAndView getEmailVerification( @RequestParam(name = "a", required = false) String id,
            @RequestParam(name = "b", required = false) String token) {
	 try {
		 Long appId	= emailVerificationService.decodeBase64ToLong(id);
		 
		 EmailVerification emailVerification =emailVerificationService.getEmailVerificationdata(appId, VerificationMode.E_mail_Link.getModeNum());
		 int isVerified = emailVerification.getIsVerified();
	
		 
	ModelAndView verificationView = new ModelAndView("emailverification");
	verificationView.addObject("applicationId", id);
    verificationView.addObject("token", token);
    verificationView.addObject("isVerified", isVerified);
 
	return verificationView;
	 }catch (Exception e) {
			throw new CommonException(ErrorCodes.SUBSETUPV004.getCode(), ErrorCodes.SUBSETUPV004.getDescription(), e);
		}
}

@PostMapping("/verificationemaillink")
public ResponseEntity<?> Emailverification(@RequestParam String token,
        @RequestParam String applicationId, String type){
	
	try {
		
		
		Long appId	= emailVerificationService.decodeBase64ToLong(applicationId);

	EmailVerification emailVerification =emailVerificationService.getEmailVerificationdata(appId, VerificationMode.E_mail_Link.getModeNum());
	
	String mailToken = emailVerification.getEmailVerificationToken();
	if (mailToken.equals(token)) {
		boolean isVerified = false;
		  if (type.equals("1")) {
			  emailVerification.setIsVerified(Status.ACTIVE.getStatusId());  
			  
		
		  EmailVerification mailVerification = emailVerificationService.saveEmailVerification(emailVerification);
		  isVerified = true;
		  }
		  return ResponseEntity.ok(isVerified);
	}else {
		return ResponseEntity.ok("Unauthorized Access");
	}
	
	
	
}
	catch (Exception e) {
		throw new CommonException(ErrorCodes.SUBSETUPV004.getCode(), ErrorCodes.SUBSETUPV004.getDescription(), e);
	}

}


@PostMapping("/verifyingotp")
public ResponseEntity<?> VerifingOTP(@RequestParam Long  applicationId,
        @RequestParam String mailOtp){
	try {
	EmailVerification emailVerification =emailVerificationService.getEmailVerificationdata(applicationId, VerificationMode.E_mail_OTP.getModeNum());
	
	String encryptedOtp = emailVerification.getEncryptedOtp();
	
	  byte[] decodedBytes = Base64.getDecoder().decode(encryptedOtp);
	  String decodedOtp = new String (decodedBytes) ;
	
	  boolean isVerified = false;
	  if (mailOtp.equals(decodedOtp)) {
		  emailVerification.setIsVerified(Status.ACTIVE.getStatusId());
		  EmailVerification updateStatus = emailVerificationService.saveEmailVerification(emailVerification);
		  isVerified = true;
	  }
	  
	  
	return ResponseEntity.ok(isVerified);

}catch (Exception e) {
	throw new CommonException(ErrorCodes.SUBSETUPV005.getCode(), ErrorCodes.SUBSETUPV005.getDescription(), e);
}
}











}
