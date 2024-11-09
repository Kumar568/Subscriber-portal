package com.emudhra.emra.subscriber.config;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.emudhra.emra.subscriber.dto.LoginRequestDto;



@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

@Autowired
	private UserServiceImpl serviceImpl;


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("inside login provider");
		CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
		LoginRequestDto dto = (LoginRequestDto) customAuthentication.getPrincipal();
		try {
			HashMap<String, String> response = 
					serviceImpl.authenticateUser(dto);

			if (response != null && (response.get("status").equals("1")))  {
				CustomAuthentication customAuth = new CustomAuthentication(null, null, dto);
				customAuth.setAuthenticated(true);
				
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes()).getRequest();

				return customAuth;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new BadCredentialsException("Invalid verification data");
		}
		throw new BadCredentialsException("Invalid verification data");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return CustomAuthentication.class.isAssignableFrom(authentication);
	}

}
