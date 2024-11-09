package com.emudhra.emra.subscriber.config;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthentication extends AbstractAuthenticationToken {

	private Object credentials;
	private final Object principal;

	public CustomAuthentication(Collection<? extends GrantedAuthority> authorities, Object credentials,
			Object principal) {
		super(authorities);

		this.credentials = credentials;
		this.principal = principal;
		setAuthenticated(false);
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

}
