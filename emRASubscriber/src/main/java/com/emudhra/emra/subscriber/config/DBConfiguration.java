package com.emudhra.emra.subscriber.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariDataSource;

@Configuration

public class DBConfiguration {

	public Properties prop = new Properties();

	private String propertyPath = "";

	public DBConfiguration(@Value("${path}") String propertyPath) {
		this.propertyPath = propertyPath;
	}

	@Bean
	public DataSource dataSource() {

		try {

			System.out.println("Given Property Path Is : ---->  " + propertyPath);
			prop = new Properties();
			propertyPath = propertyPath + File.separator + "properties\\db.properties";

			InputStream in = new FileInputStream(new File(propertyPath.replace('\\', '/')));
			prop.load(in);

			String encryptedUsername = prop.getProperty("MYSQL_DB_USERNAME");
			String encryptedPassword = prop.getProperty("MYSQL_DB_PASSWORD");

			String url = prop.getProperty("MYSQL_DB_PATH");
			String driver = prop.getProperty("MYSQL_DB_DRIVER");
			prop.getProperty("MYSQL_HIBERNATE_DIALECT");
			prop.getProperty("MYSQL_DDL");
			String username = CustomEncryption.emDecrypt(encryptedUsername);
			String decryptedUsername = username.trim().replaceAll("^\\?", "").trim();

			String password = CustomEncryption.emDecrypt(encryptedPassword);
			String decryptedPassword = password.trim().replaceAll("^\\?", "").trim();

			HikariDataSource dataSource=new HikariDataSource();
		
			dataSource.setDriverClassName(driver);
			dataSource.setJdbcUrl(url);
			dataSource.setUsername(decryptedUsername);
			dataSource.setPassword(decryptedPassword);

			dataSource.setMaximumPoolSize(8);

			return dataSource;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}