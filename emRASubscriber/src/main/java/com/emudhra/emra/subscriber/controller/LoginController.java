package com.emudhra.emra.subscriber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.emudhra.emra.subscriber.business.CaptchaGenerator;
import com.emudhra.emra.subscriber.business.CryptoUtilities;
import com.emudhra.emra.subscriber.config.CustomAuthentication;
import com.emudhra.emra.subscriber.dto.Captcha;
import com.emudhra.emra.subscriber.dto.LoginRequestDto;

@Controller
public class LoginController {

	@Autowired
	private CaptchaGenerator captchaGenerator;

	@Autowired
	private CryptoUtilities cryptoUtilities;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/login")
	public ModelAndView loginPage(Model model, HttpSession session) {
		Captcha captchaNumber = captchaGenerator.generateCaptcha();

		session.setAttribute("expectedCaptcha", captchaNumber);

		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("captchaNumber", captchaNumber);
		return modelAndView;
	}

	@ModelAttribute("login")
	public LoginRequestDto LoginReqeust() {

		return new LoginRequestDto();
	}

	@PostMapping("/auth")
	public String userLogin(@ModelAttribute("login") LoginRequestDto logindto, HttpSession session)
			throws AuthenticationException {
		Captcha expectedCaptcha = (Captcha) session.getAttribute("expectedCaptcha");

		if (expectedCaptcha != null && logindto.getSubscribecaptcha().equals(expectedCaptcha.getText())) {
			Authentication authentication = new CustomAuthentication(null, null, logindto);

			Authentication authenticated = authenticationManager.authenticate(authentication);
			SecurityContextHolder.getContext().setAuthentication(authenticated);
			authentication = SecurityContextHolder.getContext().getAuthentication();

			if (authenticated.isAuthenticated()) {
				return "redirect:/dashboard";
			} else {
				return "redirect:/login?error";
			}
		} else {
			return "redirect:/login?captchaError";
		}
	}

	@PostMapping("/encryptPassword")
	public ResponseEntity<String> encryptPassword(@RequestParam String password) {
		try {
			String encryptedPassword = cryptoUtilities.AESencrypt(password);
			return ResponseEntity.ok(encryptedPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
