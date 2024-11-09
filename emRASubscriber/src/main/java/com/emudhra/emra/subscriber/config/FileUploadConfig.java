package com.emudhra.emra.subscriber.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
//@PropertySource("@PropertySource(\"file:D:/MigrationProperties/EMRA/properties/application.properties\")\r\n" + 
		//"")

public class FileUploadConfig {
	
//	public Properties prop = new Properties();
//
//	private String propertyPath = "";
//
//	public FileUploadConfig(@Value("${path}") String propertyPath) {
//		this.propertyPath = propertyPath;
//	}
   @Value("${csr.upload.dir}")
    private String uploadDir;
	@Value("${path}")
    private String propertyPath;

    public String getUploadDir() {
        return uploadDir;
    }
	
//	System.out.println("Given Property Path Is : ---->  " + propertyPath);
//	prop = new Properties();
//	propertyPath = propertyPath + File.separator + "properties\\application-prod.properties";
//
//	InputStream in = new FileInputStream(new File(propertyPath.replace('\\', '/')));
//	prop.load(in);
//
//	String uploadpath = prop.getProperty("csr.upload.dir");
}
