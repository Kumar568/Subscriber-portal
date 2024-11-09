package com.emudhra.emra.subscriber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.emudhra.emra.subscriber.business.CaptchaGenerator;
import com.emudhra.emra.subscriber.business.CryptoUtilities;
import com.emudhra.emra.subscriber.dto.Captcha;
import com.emudhra.emra.subscriber.dto.EmailBodyDto;
import com.emudhra.emra.subscriber.dto.ForgotUserDto;

import com.emudhra.emra.subscriber.entity.entity.Customers;
import com.emudhra.emra.subscriber.entity.entity.UISettings;
import com.emudhra.emra.subscriber.entity.master.MasProperties;
import com.emudhra.emra.subscriber.enums.ErrorCodes;
import com.emudhra.emra.subscriber.exception.CommonException;
import com.emudhra.emra.subscriber.repository.MasPropertiesRepository;
import com.emudhra.emra.subscriber.repository.UISettingsRepository;
import com.emudhra.emra.subscriber.service.CustomerService;

@Controller
public class ForgotUserController {
	@Autowired
	private UISettingsRepository uISettingsRepository;
	@Autowired
	private CryptoUtilities cryptoUtilities;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private MasPropertiesRepository masPropertiesRepository;

	@Autowired
	private CaptchaGenerator captchaGenerator;

	@ModelAttribute("user")
	public ForgotUserDto LoginReqeust() {

		return new ForgotUserDto();
	}

	@PostMapping("/forgotusername")
	public ResponseEntity<String> getForgotUsername(@ModelAttribute("user") ForgotUserDto forgotUserDto, Model model,
			HttpSession session) {
		Captcha expectedCaptcha = (Captcha) session.getAttribute("expectedCaptcha");

		try {
			if (expectedCaptcha != null && forgotUserDto.getSubscribecaptcha().equals(expectedCaptcha.getText())) {

				String usernamelable = "Login ID";

				String emailId = forgotUserDto.getEmailid();

				boolean existingEmailId = customerService.isCustomerEmailExists(emailId);

				if (existingEmailId) {
					Customers customers = customerService.customerEmailDeatils(emailId);
					String username = customers.getUserName();

					UISettings us = uISettingsRepository.findById(1);

					String productName = us.getProductname();

					String mailsubject = "Subscriber Forgot User Id";

					EmailBodyDto dto = customerService.getForgotuserNameMailBody();

					String mailBodyContent = dto.getEmailBody();

					String mailbody = customerService.readFile("templates/emailtemplate.html");

					String emailFooterOne = us.getFootercontent();
					String emailFooterTwo = us.getFooterleftcontent();

					mailbody = mailbody.replaceAll("##MAILBODY##", mailBodyContent);
					mailbody = mailbody.replaceAll("##LoginID##", username);
					mailbody = mailbody.replaceAll("##productName##", productName);
					mailbody = mailbody.replaceAll("##emailFooterOne##", emailFooterOne);
					mailbody = mailbody.replaceAll("##emailFooterTwo##", emailFooterTwo);
					mailbody = mailbody.replaceAll("##registeredEmail##", emailId);
					mailbody = mailbody.replaceAll("##usernamelabel##", usernamelable);
					mailbody = mailbody.replaceAll("##usernamelist##", username);
					Boolean result = customerService.sendEmailRA(mailbody, emailId, mailsubject);
					String successMessage = "Email sent successfully";
					return ResponseEntity.ok(successMessage);
				} else {

					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found");

				}
			}
		} catch (Exception e) {

			throw new CommonException(ErrorCodes.SUBLOGIN004.getCode(), ErrorCodes.SUBLOGIN004.getDescription(), e);

		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Captcha");
	}

	@PostMapping("/forgotpassword")
	public ResponseEntity<String> getForgotPassword(@ModelAttribute("user") ForgotUserDto forgotUserDto,
			HttpSession session, Model model) {
		Captcha expectedCaptcha = (Captcha) session.getAttribute("expectedCaptcha");
		try {
			if (expectedCaptcha != null && forgotUserDto.getSubscribecaptcha().equals(expectedCaptcha.getText())) {
				String userId = "";
				String usernamelable = "Login ID";

				String username = forgotUserDto.getUsername();
				String emailId = forgotUserDto.getEmailid();

				boolean existingUserName = customerService.isCustomerUsernameExists(username);

				if (existingUserName) {
					Customers customers = customerService.ExistingUsernameDetails(username);

					Long id = customers.getId();
					userId = String.valueOf(id);

					userId = CryptoUtilities.AESencrypt(userId, "VR");
					Base64.Encoder encoder = Base64.getEncoder();
					userId = encoder.encodeToString(userId.getBytes());

					UISettings us = uISettingsRepository.findById(1);

					List<MasProperties> properties = masPropertiesRepository.findAll();
					String productName = us.getProductname();
					String EMRA_SUBSCRIBER_RESETPASSWORD_LINK = properties.get(71).getValue();
					String EMRA_SUBSCRIBER_CHANGEPASSWORD_LINK = properties.get(72).getValue();

					String SUBSCRIBER_LOGIN_URL = properties.get(99).getValue();

					String mailsubject = "Password Reset Link";

					EmailBodyDto dto = customerService.getForgotpassWordMailBody();

					String mailBodyContent = dto.getEmailBody();

					String mailbody = customerService.readFile("templates/emailtemplate.html");

					String passwordRestLink = EMRA_SUBSCRIBER_RESETPASSWORD_LINK + "?x=" + userId;
					String passwordChangeLink = EMRA_SUBSCRIBER_CHANGEPASSWORD_LINK + "?x=" + userId;

					String emailFooterOne = us.getFootercontent();
					String emailFooterTwo = us.getFooterleftcontent();

					mailbody = mailbody.replaceAll("##MAILBODY##", mailBodyContent);
					mailbody = mailbody.replaceAll("##LoginID##", username);
					mailbody = mailbody.replaceAll("##productName##", productName);
					mailbody = mailbody.replaceAll("##emailFooterOne##", emailFooterOne);
					mailbody = mailbody.replaceAll("##emailFooterTwo##", emailFooterTwo);
					mailbody = mailbody.replaceAll("##registeredEmail##", emailId);
					mailbody = mailbody.replaceAll("##usernamelabel##", usernamelable);
					mailbody = mailbody.replaceAll("##usernamelist##", username);
					mailbody = mailbody.replaceAll("##passwordResetLink##",
							"<a href=\"" + passwordRestLink + "\">" + passwordRestLink + "</a>");
					mailbody = mailbody.replaceAll("##changePasswordLink##",
							"<a href=\"" + passwordChangeLink + "\">" + passwordChangeLink + "</a>");
					mailbody = mailbody.replaceAll("##subscriberLoginURL##",
							"<a href=\"" + SUBSCRIBER_LOGIN_URL + "\">" + SUBSCRIBER_LOGIN_URL + "</a>");

					Boolean result = customerService.sendEmailRA(mailbody, emailId, mailsubject);
					model.addAttribute("successMessage", "Email sent successfully");

					return ResponseEntity.ok("Email sent successfully");
				} else {

					model.addAttribute("successMessage", null); // Set the success message to null
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found");
				}
			}
		} catch (Exception e) {

			throw new CommonException(ErrorCodes.SUBLOGIN003.getCode(), ErrorCodes.SUBLOGIN003.getDescription(), e);

		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Captcha");
	}

	@GetMapping("/resetpassword")
	public ModelAndView getResetView(@RequestParam("x") String id, HttpSession session) {
		ModelAndView subView = new ModelAndView("resetpassword");

		try {
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] decodedBytes = decoder.decode(id);

			String decryptedUserId = CryptoUtilities.AESdecrypt(new String(decodedBytes, StandardCharsets.UTF_8), "VR");
			Long userId = Long.parseLong(decryptedUserId);
			Customers customers = customerService.customerDeatils(userId);

			Captcha captchaNumber = captchaGenerator.generateCaptcha();
			session.setAttribute("expectedCaptcha", captchaNumber);

			subView.addObject("captchaNumber", captchaNumber);

			subView.addObject("customers", customers);
		} catch (Exception e) {

			e.printStackTrace();
			throw new CommonException(ErrorCodes.SUBLOGIN005.getCode(), ErrorCodes.SUBLOGIN005.getDescription(), e);

		}

		return subView;
	}

	@GetMapping("/changepassword")
	public ModelAndView getChangePasswordView(@RequestParam("x") String id, HttpSession session) {
		ModelAndView subView = new ModelAndView("changepassword");

		try {
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] decodedBytes = decoder.decode(id);

			String decryptedUserId = CryptoUtilities.AESdecrypt(new String(decodedBytes, StandardCharsets.UTF_8), "VR");
			Long userId = Long.parseLong(decryptedUserId);
			Customers customers = customerService.customerDeatils(userId);

			Captcha captchaNumber = captchaGenerator.generateCaptcha();
			session.setAttribute("expectedCaptcha", captchaNumber);
			subView.addObject("captchaNumber", captchaNumber);

			subView.addObject("customers", customers);
		} catch (Exception e) {

			e.printStackTrace();
			throw new CommonException(ErrorCodes.SUBLOGIN006.getCode(), ErrorCodes.SUBLOGIN006.getDescription(), e);

		}

		return subView;
	}

	@PostMapping("/updateresetpassword")
	public ResponseEntity<String> updateResetPassword(@RequestBody ForgotUserDto forgotUserDto, HttpSession session) {
		Captcha expectedCaptcha = (Captcha) session.getAttribute("expectedCaptcha");
		try {
			if (expectedCaptcha != null && forgotUserDto.getSubscribecaptcha().equals(expectedCaptcha.getText())) {
				String username = forgotUserDto.getUsername();
				String password = forgotUserDto.getPassword();
				String email = forgotUserDto.getEmailid();
				String captcha = forgotUserDto.getSubscribecaptcha();

				Customers existingcustomers = customerService.ExistingUsernameDetails(username);
				String encryptedpassword = CryptoUtilities.AESencrypt(password, "VR");
				Base64.Encoder encoder = Base64.getEncoder();
				encryptedpassword = encoder.encodeToString(encryptedpassword.getBytes());
				existingcustomers.setPassword(encryptedpassword);

				customerService.saveCustomers(existingcustomers);
				return ResponseEntity.ok("User validity updated successfully.");
			} else {
				// Handle the case when the captcha does not match
				return ResponseEntity.badRequest().body("Invalid captcha. Please try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException(ErrorCodes.SUBLOGIN007.getCode(), ErrorCodes.SUBLOGIN007.getDescription(), e);

		}
	}

	@PostMapping("/changepassword")
	public ResponseEntity<String> changePassword(@RequestBody ForgotUserDto forgotUserDto, HttpSession session) {
		try {
			String result = customerService.changePassword(forgotUserDto, session);
			if (result.equals("success")) {
				return ResponseEntity.ok("User password changed successfully.");
			} else if (result.equals("password_mismatch")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Password mismatch. Please check your current password.");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("An error occurred while updating the user's password.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException(ErrorCodes.SUBLOGIN008.getCode(), ErrorCodes.SUBLOGIN008.getDescription(), e);

		}
	}

}
