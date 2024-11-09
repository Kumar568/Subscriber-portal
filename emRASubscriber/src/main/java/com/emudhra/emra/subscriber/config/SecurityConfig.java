package com.emudhra.emra.subscriber.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private LoginAuthenticationProvider authenticationProvider;

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception{
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
	}


	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.csrf().disable().authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/auth").permitAll()
		.antMatchers("/aes/**").permitAll()
		.antMatchers("/dashboard").authenticated()
		.antMatchers("/usercreation/**").permitAll()
			.antMatchers("/**", "/js/**", "/css/**", "/img/**").permitAll().
			 anyRequest().authenticated().and()
			.formLogin().loginPage("/login")
			.permitAll().and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login?logout")
			.invalidateHttpSession(true).clearAuthentication(true)
			.deleteCookies("JSESSIONID")
			.permitAll()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.invalidSessionUrl(null)
			.maximumSessions(1)
			.maxSessionsPreventsLogin(false);
    	  return http.build();

    }
}
