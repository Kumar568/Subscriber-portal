package com.emudhra.emra.subscriber.config;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.emudhra.emra.subscriber.entity.master.MasProperties;
import com.emudhra.emra.subscriber.repository.MasPropertiesRepository;




@Configuration
public class EmailConfig {

	   @Autowired
	   private MasPropertiesRepository masPropertiesRepository;
	    @Bean
	    public JavaMailSender javaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            
	        
	        List<MasProperties> masProperties =masPropertiesRepository.findAll();
	        
	        Map<String, String> emailProperties= masProperties.stream()
	            .collect(Collectors.toMap(MasProperties::getName, MasProperties::getValue));
	   
	        // Configure the JavaMailSender with the retrieved properties
	        mailSender.setHost(emailProperties.get("EMAIL_HOST"));
	        mailSender.setPort(Integer.parseInt(emailProperties.get("EMAIL_PORT")));
	        mailSender.setUsername(emailProperties.get("EMAIL_USER"));
	        mailSender.setPassword(emailProperties.get("EMAIL_PASSWORD"));
	      
	        // Additional email properties (optional)
	        Properties properties = new Properties();
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        mailSender.setJavaMailProperties(properties);

	        return mailSender;
	    }

}
