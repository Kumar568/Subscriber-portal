package com.emudhra.emra.subscriber.controller;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.emudhra.emra.subscriber.business.CaptchaGenerator;
import com.emudhra.emra.subscriber.business.CryptoUtilities;
import com.emudhra.emra.subscriber.dto.Captcha;
import com.emudhra.emra.subscriber.dto.CreateOrderDto;
import com.emudhra.emra.subscriber.entity.entity.Customers;
import com.emudhra.emra.subscriber.enums.ErrorCodes;
import com.emudhra.emra.subscriber.exception.CommonException;
import com.emudhra.emra.subscriber.service.GeneratePasswordService;



@RestController
public class GeneratePasswordController {
	 @Autowired
	    private CryptoUtilities cryptoUtilities;
	@Autowired
	private CaptchaGenerator captchaGenerator;
	
	@Autowired
	private  GeneratePasswordService generatePasswordService;
	
	@GetMapping("/subscriber/generatepassword")
	public ModelAndView getView(@RequestParam("x") String id, HttpSession session) {
	    ModelAndView subView = new ModelAndView("generatepassword");

	    try {
	    	Base64.Decoder decoder = Base64.getDecoder();
	    	byte[] decodedBytes = decoder.decode(id);
	    	
	    	
	    	  
	    	String decryptedUserId = CryptoUtilities.AESdecrypt(new String(decodedBytes, StandardCharsets.UTF_8), "VR");
	    	Long userId = Long.parseLong(decryptedUserId);
	        
	    	Customers customers = generatePasswordService.getCustomersData(userId);
	    	
	    	
	        Captcha captchaNumber = captchaGenerator.generateCaptcha();
	        
	        session.setAttribute("captcha", captchaNumber);
	        
	        subView.addObject("captchaNumber", captchaNumber);
	        subView.addObject("customers", customers);
	    } catch (Exception e) {
	        // Handle the exception. You can log it, show an error message, or perform any necessary actions.
	        // For example, logging the exception:
	        e.printStackTrace();
	        // You might want to add an error message to the view:
	        subView.addObject("errorMessage", "An error occurred while processing your request.");
	    }

	    return subView;
	}

	
	@PutMapping("/savecustomerpassword")
	public ResponseEntity<String> saveCustomerPassword(@RequestBody CreateOrderDto dto, HttpSession session) {
	    String userName = dto.getUserName();
	    String password = dto.getPassword(); // Renamed Password to password (variables should start with a lowercase letter)
	    String captchaText = dto.getCaptcha(); // Renamed capcha to captchaText for clarity

	    Captcha captcha = (Captcha) session.getAttribute("captcha");
	    String captchaValue = captcha.getText();

	    if (captchaValue.equalsIgnoreCase(captchaText)) {
	        Customers customers = generatePasswordService.getCustomersByUserName(userName);

	        if (customers != null) { // Check if the customer exists

	            try {
	                // Encrypt the password and encode it in Base64
	                password = CryptoUtilities.AESencrypt(password, "VR");
	                Base64.Encoder encoder = Base64.getEncoder();
	                password = encoder.encodeToString(password.getBytes());

	                // Update the customer's password and set other fields
	                customers.setPassword(password);
	                customers.setUpdatedDate(LocalDateTime.now());
	                customers.setUpdatedBy(String.valueOf(customers.getId())); // Assuming you want to set the ID as the updatedBy value

	                Customers updatedCustomer = generatePasswordService.saveCustomers(customers);

	                return ResponseEntity.ok("Password Updated successfully");
	            } catch (Exception e) {
	                e.printStackTrace();
	                throw new CommonException(ErrorCodes.ADMINMIU001.getCode(), ErrorCodes.ADMINMIU001.getDescription(), e);
	            }
	        } else {
	            return ResponseEntity.ok("Customer not found");
	        }
	    } else {
	        return ResponseEntity.ok("Enter Valid Captcha");
	    }
	}

	
	
}
