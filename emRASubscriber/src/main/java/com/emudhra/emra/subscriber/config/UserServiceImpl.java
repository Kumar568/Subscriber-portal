package com.emudhra.emra.subscriber.config;

import java.util.Base64;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.emudhra.emra.subscriber.business.CryptoUtilities;
import com.emudhra.emra.subscriber.dto.LoginRequestDto;
import com.emudhra.emra.subscriber.entity.entity.Customers;
import com.emudhra.emra.subscriber.repository.CreateOrderRepository;
import com.emudhra.emra.subscriber.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private CryptoUtilities cryptoUtilities;
	@Autowired
	private CreateOrderRepository createOrderRepository;

	@Override
	public HashMap<String, String> authenticateUser(LoginRequestDto loginRequestDto) throws Exception {

		String password = loginRequestDto.getPassword();

		String encryptedpassword = CryptoUtilities.AESencrypt(password, "VR");
		Base64.Encoder encoder = Base64.getEncoder();
		encryptedpassword = encoder.encodeToString(encryptedpassword.getBytes());
		HashMap<String, String> response = new HashMap<>();
		Customers customer = createOrderRepository.findByUserName(loginRequestDto.getUsername());

		if (customer != null) {
			if (passwordMatches(customer.getPassword(), encryptedpassword)) {
				response.put("status", "1");

				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes()).getRequest();
				HttpSession session = request.getSession();
				session.setAttribute("username", customer.getUserName());
				session.setAttribute("user", customer);
			}
		}

		return response;
	}

	private boolean passwordMatches(String storedEncryptedPassword, String inputPassword) {

		return storedEncryptedPassword.equals(inputPassword);
	}

}
