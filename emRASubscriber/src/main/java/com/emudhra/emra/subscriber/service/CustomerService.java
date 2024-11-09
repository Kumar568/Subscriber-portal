package com.emudhra.emra.subscriber.service;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequest;
import org.bouncycastle.util.io.pem.PemObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;


import com.emudhra.emra.subscriber.business.CryptoUtilities;
import com.emudhra.emra.subscriber.business.Utilities;
import com.emudhra.emra.subscriber.dto.AdditionalField;
import com.emudhra.emra.subscriber.dto.Captcha;
import com.emudhra.emra.subscriber.dto.DataTableResponse;
import com.emudhra.emra.subscriber.dto.EmailBodyDto;
import com.emudhra.emra.subscriber.dto.FieldGroupDto;
import com.emudhra.emra.subscriber.dto.ForgotUserDto;
import com.emudhra.emra.subscriber.dto.OrderDetailsDto;
import com.emudhra.emra.subscriber.entity.entity.Application;
import com.emudhra.emra.subscriber.entity.entity.Customers;
import com.emudhra.emra.subscriber.entity.entity.EmailContent;
import com.emudhra.emra.subscriber.entity.entity.FieldGroup;
import com.emudhra.emra.subscriber.entity.entity.OrderDetails;
import com.emudhra.emra.subscriber.entity.entity.UISettings;
import com.emudhra.emra.subscriber.enums.EmailType;
import com.emudhra.emra.subscriber.enums.ErrorCodes;
import com.emudhra.emra.subscriber.exception.CommonException;
import com.emudhra.emra.subscriber.mapper.FieldGroupMapper;
import com.emudhra.emra.subscriber.mapper.OrderDetailsMapper;
import com.emudhra.emra.subscriber.repository.ApplicationRepository;
import com.emudhra.emra.subscriber.repository.CreateOrderRepository;
import com.emudhra.emra.subscriber.repository.EmailContentRepository;
import com.emudhra.emra.subscriber.repository.FieldGroupRepository;
import com.emudhra.emra.subscriber.repository.OrderDetailsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerService {
	static Provider bcProvider = new BouncyCastleProvider();
	@Autowired
	private FieldGroupRepository fieldGroupRepository;
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private CreateOrderRepository createOrderRepository;
	@Autowired
	private EmailContentRepository emailContentRepository;
	  @Autowired
	    private CryptoUtilities cryptoUtilities;
	  @Autowired
	    private OrderDetailsRepository orderDetailsRepository;
	  @Autowired
		private UISettingsService uiSettingsService;
	  @Autowired
		private MasPropertiesService masPropertiesServices;
		@Autowired
		private Utilities utilities;
		@Autowired
		private ApplicationRepository applicationRepository;
	  
	  @Autowired
	    private  OrderDetailsMapper orderDetailsMapper;
	  @Autowired
	    private FieldGroupMapper fieldGroupMapper;

	  
private JavaMailSender javaMailSender;
	
	@Autowired
		public CustomerService(JavaMailSender javaMailSender) {
			this.javaMailSender = javaMailSender;
		}
	public Customers saveCustomers(Customers customers) {
	    try {
	        return createOrderRepository.save(customers);
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return null; 
	    }
	}


	public boolean isCustomerEmailExists(String emailId) {
	    try {
	        Customers user = createOrderRepository.findByEmailId(emailId);
	        return user != null;
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return false; 
	    }
	}


	public boolean isCustomerUsernameExists(String userName) {
	    try {
	        Customers user = createOrderRepository.findByUserName(userName);
	        return user != null;
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return false; 
	    }
	}


	
	public Customers ExistingUsernameDetails(String userName) {
	    try {
	    	Customers user = createOrderRepository.findByUserName(userName);
			return user;
	    } catch (Exception e) {

	        e.printStackTrace(); 
	        return null; 
	    }
	}

	public Customers customerEmailDeatils(String emailId) {
	    try {
	        Customers userdetails = createOrderRepository.findByEmailId(emailId);
	        return userdetails;
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return null; 
	    }
	}

	public Customers customerDeatils(Long userid) {
	    try {
	        Customers user = createOrderRepository.findById(userid).get();
	        return user;
	    } catch (NoSuchElementException e) {
	        return null; 
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        return null; 
	    }
	}


	
	public String changePassword(ForgotUserDto forgotUserDto, HttpSession session) {
	    Captcha expectedCaptcha = (Captcha) session.getAttribute("expectedCaptcha");
	    try {
	        if (expectedCaptcha != null && forgotUserDto.getSubscribecaptcha().equals(expectedCaptcha.getText())) {
	            Long userId = forgotUserDto.getUserId();
	            String password = forgotUserDto.getPassword();
	            String currentPassword = forgotUserDto.getCurrentpassword();
	            String captcha = forgotUserDto.getSubscribecaptcha();

	            // Retrieve the existing customer
	            Customers existingCustomers = customerDeatils(userId);

	            String existingPassword = existingCustomers.getPassword();

	            // Encrypt the current password
	            String encryptedCurrentPassword = CryptoUtilities.AESencrypt(currentPassword, "VR");
	            Base64.Encoder encoder = Base64.getEncoder();
	            encryptedCurrentPassword = encoder.encodeToString(encryptedCurrentPassword.getBytes());

	            if (passwordMatches(existingPassword, encryptedCurrentPassword)) {
	                // Encrypt the new password
	                String encryptedChangePassword = CryptoUtilities.AESencrypt(password, "VR");
	                Base64.Encoder changePasswordEncoder = Base64.getEncoder();
	                encryptedChangePassword = changePasswordEncoder.encodeToString(encryptedChangePassword.getBytes());

	                existingCustomers.setPassword(encryptedChangePassword);

	                saveCustomers(existingCustomers);

	                return "success"; 
	            } else {
	                return "password_mismatch"; 
	            }
	        } else {
	            return "captcha_mismatch"; 
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}

	

	
	  
    private boolean passwordMatches(String storedEncryptedPassword, String inputPassword) {
  
        return storedEncryptedPassword.equals(inputPassword); 
    }
	

    
    public EmailBodyDto getForgotuserNameMailBody() {
    	
    	try {
    		EmailContent emailContent = emailContentRepository.
    				findByMasEmailTypeSlNo(EmailType.FORGOT_USERNAME.getCode());
    		
    		EmailBodyDto dto =new EmailBodyDto();
    		dto.setEmailBody(emailContent.getEmailBody());
    		
    		return dto;
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		return null;
		}
		
    	

	}
	public EmailBodyDto getForgotpassWordMailBody() {
		try {
			EmailContent emailContent = emailContentRepository.
					findByMasEmailTypeSlNo(EmailType.PASSWORD_RESET.getCode());
			
			EmailBodyDto dto =new EmailBodyDto();
			dto.setEmailBody(emailContent.getEmailBody());
			
			return dto;
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		
	}
	
	public String readFile(String filePath) {
	    try {
	        Resource resource = resourceLoader.getResource("classpath:" + filePath);
	        try (InputStream in = resource.getInputStream()) {
	            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
	                StringBuilder buffer = new StringBuilder(2048);
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    buffer.append(line);
	                }
	                return buffer.toString();
	            }
	        }
	    } catch (IOException e) {
	       
	        e.printStackTrace(); // or other error handling
	        throw new CommonException(ErrorCodes.ADMINHR001.getCode(), ErrorCodes.ADMINHR001.getDescription(), e);
	}

	}
    public static int getRandom() throws Exception {
        try {
            Random rand = new Random();
            int n = rand.nextInt(90000) + 10000;
            return n;
        } catch (Exception e) {
        	e.printStackTrace(); // You can replace this with your error-handling logic.
			throw new CommonException(ErrorCodes.SUBS0004.getCode(), ErrorCodes.SUBS0004.getDescription(), e);
           
        }
    }
	
	 public  String getRandomCustomerToken() throws Exception {
	        SecureRandom random = new SecureRandom();
	        byte[] rdnBytes = new byte[128 / 8];
	        random.nextBytes(rdnBytes);
	        String hex = DatatypeConverter.printHexBinary(rdnBytes);
	        return hex;
	    }
	    
	    public String generateUniqueFileName() {
	        // Generate a random UUID (Universally Unique Identifier)
	        UUID uuid = UUID.randomUUID();
	        return uuid.toString();
	    }

	    public  String generateDocumentHash(byte[] content) throws NoSuchAlgorithmException {
	        try {
	            MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
	            byte[] hashBytes = sha256Digest.digest(content);

	            // Convert bytes to Base64 representation
	            String base64String = Base64.getEncoder().encodeToString(hashBytes);

	            // Replace slashes with another character (e.g., underscore)
	            String sanitizedBase64String = base64String.replace("\\", "").replace("/", "");

	            return sanitizedBase64String;
	        } catch (Exception e) {
	        	throw new CommonException(ErrorCodes.SUBSETUPV006.getCode(), ErrorCodes.SUBSETUPV006.getDescription(), e);
	        }
	    }

	
	
	
	public Boolean sendEmailRA(String mailBody, String toEmail, String subject) {
		
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

 

			// Set email recipient, subject, and body
			helper.setTo(toEmail);
			helper.setSubject(subject);
			helper.setText(mailBody, true);

 

			// Send the email
	            javaMailSender.send(message);
	            return true;
		} catch (MessagingException e) {

			e.printStackTrace(); // You can replace this with your error-handling logic.
			return false;
		}
	}
	
	
	public DataTableResponse<OrderDetailsDto> getamanagecertifivatelist(Customers customers, Pageable pageable) {
		Page<OrderDetails> pageResult = orderDetailsRepository.findByCustomers(customers,pageable);

		List<OrderDetailsDto> orderDetailsDto = orderDetailsMapper.mapOrderDetailsToOrderDetailsDto(pageResult.getContent());

		long recordsTotal = pageResult.getTotalElements();

		DataTableResponse<OrderDetailsDto> datatable = new DataTableResponse<>(orderDetailsDto, recordsTotal, recordsTotal);

		return datatable;

	}
	public OrderDetailsDto getorderdetails(Long id) {
	    Optional<OrderDetails> orderDetailsOptional = orderDetailsRepository.findById(id);

	    if (orderDetailsOptional.isPresent()) {
	        OrderDetails orderDetails = orderDetailsOptional.get();
	        OrderDetailsDto orderDetailsDto = orderDetailsMapper.mapOrderDetailsToOrderDetailsDto(orderDetails);
	        return orderDetailsDto;
	    } else {
	        // Handle the case where the OrderDetails with the given ID is not found
	        return null; // Or throw an exception, return a default DTO, etc.
	    }
	}
	
	
	public FieldGroupDto getfieldgroupdetails(Long id) {
	    Optional<FieldGroup> fieldGroupDetails = fieldGroupRepository.findById(id);

	    if (fieldGroupDetails.isPresent()) {
	    	FieldGroup fieldGroup =  fieldGroupDetails.get();
	    FieldGroupDto fieldGroupDto = fieldGroupMapper.mapFieldGroupToFieldGroupDto(fieldGroup);
	        return fieldGroupDto;
	    } else {
	        // Handle the case where the OrderDetails with the given ID is not found
	        return null; // Or throw an exception, return a default DTO, etc.
	    }
	}
	public String getEMailBody(String verifingOtp) {
		try {
		 Map<String, String>   properties = masPropertiesServices.getMasPropertiesData();
	  	  
	  	 UISettings us = uiSettingsService.getUISettings();
	  	 		String projectName = us.getProductname();
	  	 		String emailFooterOne = us.getFootercontent();
	  			String emailFooterTwo = us.getFooterleftcontent();
//	  			int otp = getRandom();
//	  			String verifingOtp = String.valueOf(otp);
//	  			session.setAttribute("emailverificationotp", verifingOtp);
	  			
	        EmailBodyDto dto =  utilities.getMailBody(EmailType.EMAIL_OTP.getCode());
	        
	    	String mailBodyContent = dto.getEmailBody();
	 
	        String mailbody =readFile("templates/emailtemplate.html");
	        
	        
	        mailbody = mailbody.replaceAll("##MAILBODY##", mailBodyContent);
	        mailbody = mailbody.replaceAll("##contactAddress##", properties.get("RA_ADMIN_CONTACT_ADDRESS"));
			mailbody = mailbody.replaceAll("##supportEmailId##", properties.get("CONTACT_SUPPORT_EMAILID"));
			mailbody = mailbody.replaceAll("##productName##",		projectName);
			mailbody = mailbody.replaceAll("##emailFooterOne##", emailFooterOne);
			mailbody = mailbody.replaceAll("##emailFooterTwo##", emailFooterTwo);
			mailbody = mailbody.replaceAll("##OTP##", verifingOtp);
	        
	     
		return mailbody;
		}catch (Exception e) {
			e.printStackTrace(); 
			throw new CommonException(ErrorCodes.SUBS0005.getCode(), ErrorCodes.SUBS0005.getDescription(), e);
		}
		
	}
	
	public String getEmailLinkBody(String applicationId, String token) {
		try {
		 Map<String, String>   properties = masPropertiesServices.getMasPropertiesData();
	  	  
		 String infinetSubscriberUrl = "";
			String Activation_Link = "";
			String emailVerificationLink = "";
			String Activation_infinetLinkUrl = "";
			String infinetLinkUrl = "";
	  	 UISettings us = uiSettingsService.getUISettings();
	  	 		String projectName = us.getProductname();
	  	 		String emailFooterOne = us.getFootercontent();
	  			String emailFooterTwo = us.getFooterleftcontent();
	  			
	  			String SUBSCRIBER_LOGIN_URL = properties.get("SUBSCRIBER_LOGIN_URL");
	  			String URL_TO_REPLACE_INFINET = properties.get("INTERNET_URL_TO_REPLACE");
	  			String INFINET_URL_HOST = properties.get("INFINET_URL");
	  			emailVerificationLink = properties.get("EMRA_SUBSCRIBER_EMAILVERIFICATIONLINK");
				Activation_Link = emailVerificationLink + "?a=" + applicationId
						+ "&b=" + token;
				Activation_infinetLinkUrl = infinetLinkUrl + "?a="
						+ applicationId + "&b="
						+ token;

	  			if (SUBSCRIBER_LOGIN_URL.contains(URL_TO_REPLACE_INFINET)) {
	  				infinetSubscriberUrl = SUBSCRIBER_LOGIN_URL.replaceAll(URL_TO_REPLACE_INFINET, INFINET_URL_HOST);
				}
	  			if (emailVerificationLink.contains(URL_TO_REPLACE_INFINET)) {
	  				infinetLinkUrl = emailVerificationLink.replaceAll(URL_TO_REPLACE_INFINET, INFINET_URL_HOST);
	  			}
	  			
	  			EmailBodyDto mailDto = utilities.getMailBody(EmailType.EMAIL_VERIFICATION_LINK.getCode());
	  		   	String maillinkBodyContent = mailDto.getEmailBody();
	  		   	
	  		  String mailbody =readFile("templates/emailtemplate.html");
	  		mailbody = mailbody.replaceAll("##MAILBODY##", maillinkBodyContent);
	  		mailbody = mailbody.replaceAll("##subscriberLoginURL##", properties.get("SUBSCRIBER_LOGIN_URL"));
	  		mailbody = mailbody.replaceAll("##contactAddress##", properties.get("RA_ADMIN_CONTACT_ADDRESS"));
			mailbody = mailbody.replaceAll("##supportEmailId##", properties.get("CONTACT_SUPPORT_EMAILID"));
			mailbody = mailbody.replaceAll("##productName##",		projectName);
			mailbody = mailbody.replaceAll("##emailFooterOne##", emailFooterOne);
			mailbody = mailbody.replaceAll("##emailFooterTwo##", emailFooterTwo);
			mailbody = mailbody.replaceAll("##infinetSubscriberLoginURL##", infinetSubscriberUrl);
			mailbody = mailbody.replaceAll("##EMAIL VERIFICATION##", "<a href=\"" + Activation_Link
					+ "\"style=\"font-size:14px; font-weight: bold; color:#6ea54a; display:inline-block; text-decoration:none;\">Verify Email</a>");
			mailbody = mailbody.replaceAll("##LINK##",
					"<a href=\"" + Activation_Link + "\">" + Activation_Link + "</a>");
			mailbody = mailbody.replaceAll("##EMAIL VERIFICATION THROUGH INFINET##", "<a href=\""
					+ Activation_infinetLinkUrl
					+ "\"style=\"font-size:14px; font-weight: bold; color:#6ea54a; display:inline-block; text-decoration:none;\">Verify Email</a>");
	  		
			mailbody = mailbody.replaceAll("##INFINETLINK##",
					"<a href=\"" + Activation_infinetLinkUrl + "\">" + Activation_infinetLinkUrl + "</a>");
	  		
	  		  
	  		 return  mailbody;
	}catch (Exception e) {
		e.printStackTrace(); 
		throw new CommonException(ErrorCodes.SUBS0005.getCode(), ErrorCodes.SUBS0005.getDescription(), e);
	}
	}
	
	
	
	
	
public String subjectDnDetails(Long applicationNum, HttpSession session) {
	try {  
		
		String subjectDnDetailsStrPFX ="";
	  List<String> subjectDNDetailsPFX = new ArrayList<String>();
		
		
	Application applicationDetails = applicationRepository.findByApplicationNumber(applicationNum);
	
	String additionalFields = applicationDetails.getAdditionalFields();
		
	ObjectMapper objectMapper = new ObjectMapper();
    List<AdditionalField> additionalFieldList = objectMapper.readValue(
            additionalFields,
            new TypeReference<List<AdditionalField>>() {});
    
    List<AdditionalField> addFields = additionalFieldList.stream()
            .filter(field -> !field.getValue().isEmpty())
            .collect(Collectors.toList());
		
    for (AdditionalField field : addFields) {
        subjectDNDetailsPFX.add(field.getFieldName() + "=" + field.getValue());
    }
     
    String defaultFields = "[\n" +
    	    "  {\n" +
    	    "    \"fieldname\": \"common_name\",\n" +
    	    "    \"fielddisplayname\": \"Common Name\",\n" +
    	    "    \"value\": \"test@\",\n" +
    	    "    \"oid\": \"4.3.2\",\n" +
    	    "    \"ismandatory\": 1,\n" +
    	    "    \"alphabetsvalidation\": 1,\n" +
    	    "    \"numberscalidation\": 0,\n" +
    	    "    \"specialcharactersValidation\": 1,\n" +
    	    "    \"israisedquery\": 1\n" +
    	    "  },\n" +
    	    "  {\n" +
    	    "    \"fieldname\": \"organization\",\n" +
    	    "    \"fielddisplayname\": \"Organization\",\n" +
    	    "    \"value\": \"emudhra\",\n" +
    	    "    \"oid\": \"4.3.2\",\n" +
    	    "    \"ismandatory\": 1,\n" +
    	    "    \"alphabetsvalidation\": 1,\n" +
    	    "    \"numberscalidation\": 0,\n" +
    	    "    \"specialcharactersValidation\": 1,\n" +
    	    "    \"israisedquery\": 1\n" +
    	    "  },\n" +
    	    "  {\n" +
    	    "    \"fieldname\": \"organization_unit\",\n" +
    	    "    \"fielddisplayname\": \"Organization Unit\",\n" +
    	    "    \"value\": \"\",\n" +
    	    "    \"oid\": \"4.3.2\",\n" +
    	    "    \"ismandatory\": 1,\n" +
    	    "    \"alphabetsvalidation\": 1,\n" +
    	    "    \"numberscalidation\": 0,\n" +
    	    "    \"specialcharactersValidation\": 1,\n" +
    	    "    \"israisedquery\": 1\n" +
    	    "  }\n" +
    	    "]";


			
  
    List<AdditionalField> defaultFieldList = objectMapper.readValue(
    		defaultFields,
            new TypeReference<List<AdditionalField>>() {});
    
    List<AdditionalField> dFields = defaultFieldList.stream()
            .filter(field -> !field.getValue().isEmpty())
            .collect(Collectors.toList());
    
	
    for (AdditionalField field : dFields) {
        subjectDNDetailsPFX.add(field.getFieldDisplayName() + "=" + field.getValue());
    }
			
    String csrPEM = applicationDetails.getCsr();
    
//    String csrPEM = "LS0tLS1CRUdJTiBDRVJUSUZJQ0FURSBSRVFVRVNULS0tLS0KTUlJRWdqQ0NBbW9DQVFBd0V6RVJNQThHQTFVRUF4TUlZMmhyYm05Mk1UY3dnZ0lpTUEwR0NTcUdTSWIzRFFFQgpBUVVBQTRJQ0R3QXdnZ0lLQW9JQ0FRQ3NxUE0wR0IvaCtXUk1kZGQ2UWdjZ1J2UHptRkxuSElnZlMxbTVpYWkrCkdzNDhiMjFBYjJhSGpSUit0V3ZkMVI2M3pNUXVEbXFWZUc1U3plcFNrVW9PSDNoN1dyaVY5MVc0NEZyQUk1QjAKeE82MG1FRDZ3WjNEeWRTZUhiVUpxcGw5c3FObjAvOEpnQnl0YURqTytkWjAxNXhidk16N3NTSkd6dnZuR202aApjUG94S3FOelJKZjJmWktxZ05xcVh3ZGpBaEFDZ3ZzdW95dTBuK1NCdzBLS1BQUnRFQUkvcjNzQWhoUUhCNmJVCkxBQkZLdXFGOFZCQnp6cTN0OTdPZkw1NTQ1TjBIWTBiNk80ajVCUUczODdUU2ZaN2RHcnhRS1VlUzNPMjczZ3MKWDB2S3JhbUczS2pJSDBzdkdwTTVyTnhTdGl6dktoTWdiclo5RXF6VGFmVzFCR0RESWFtU3M4K0xnZjZ4MzNCeApJY2hKTXN2RldoWStTb2s2aUFYWld6a2FlUldjUWIxTi96Ukh6OXhxZFFHK29WS1NMZjVXbVB3N0VNdG5jMWt6CkJwMWZneEY4TmdzcHVNd2NKU1lNZFM0MlV2MERUSmp3UytTNlk2czRmbGpjQ0E4K3l5UlRtQTM1eEREemlEZm0KdjVpTXdqRWFjeVN6eC83T2VJcTlhQTJ2RHRzaGVWMC9sdUQrdDd4ZTFHZWk4SVZIZVJOck5EQWgyZHd3dG9nNwovdXR0bXJxVWxFQWhOaE1hVUx0NnhVSTlMRTlub0tCRS9FKys2M1RqR0xtaW5GMzA2SHNCNXBnT0NLUWo5MjZOCmpaS2pjNjhramhERFR2RWdYWDJzMGMzQmRTaFlET3o4SmQwb2FkWVdqZUdPL0NEeDdTUUJuaHhPWHoxcUJ4NFIKc3dJREFRQUJvQ293S0FZSktvWklodmNOQVFrT01Sc3dHVEFYQmdOVkhSRUVFREFPZ2d3eE1DNDRNQzR4TURZdQpOell3RFFZSktvWklodmNOQVFFRkJRQURnZ0lCQUQ4SXVVb1hrMlhsZWo5VmhLczNWekVIaVM2ZnJteExuUUhFCkNhR2F1bnoxdkc3SEJETnltUDd1RjhnZS91KzJMcGpHbGduVUQyTGEwUUZUQ0RzcUFSYmZodm9ZWWpscUczQVgKUnBrbGtqS2I0eU5oL21FcmRDS3FOYUpQRGVMSEZ5bTMydlY2VWNSRTNYSEVkMzczOGFnUXlMVEFUVjVmUzhsZwpVcGtiTGIvaVRCOE4wbkJHN3lwaFQ0bGx2RFQ1eVFFUUdQUUQ2dFlpaXFwamMxbkg2RmM2Z3NGdnhQd1BQRElECkJTd1hSNVJxaWRnL00vWVhTdklMR1huMHJlS2lPWlphT2gwcFFXK1cxcFhUK05hTlppcEdQRVBFc01OY0EwazYKaDM2bjg2MGxmbUViOXlpZ0tqdVBsVUp1bnBKa2NmVUZTNldXY2dvM0RVY3BYQXhFT1BBU0p4VVg5cTJQSUg0WApoeHFOUzZGZGk5cU9HMWZwbE8rYnZ1ZGJ3WkkxUlFpUWxQbGYzNUtEcllDMUVZNmU4TEVMd0lNR1djbTBRdXRiCmJtK3VXNWZLUUR6cmtMSDB4NWxjbjJKYldGelEyNUg1b1ZmcTFvK0NaU3dzVlJldjZxMFN5emJCeE15WFVQZlcKL1pEajRpaXRUYy9KZzcrVkE4WkJCcVBCZzQ2aitZN1RKVXNMMlNjMWlCK0I2YnloTnZuSUFHeTEvZ2RZNi9yNgp6Wk5mQWZGS2dPeEpMR1JXSE4xcmhpNHJDWDdORHVTSW4ybERrT1E0dkpDNVQ1NjRjbXBBQjhId0FhWUFCYnQ5CjBaNmJwOEgwcGw2M3lIY05oYTdQZTFMY1g3QnkzNUVPSTR5YmtKM0NWQkUwN0JiOG1mR0VPMUZnWEZvZlU3V2EKQ1NrbExrTlMKLS0tLS1FTkQgQ0VSVElGSUNBVEUgUkVRVUVTVC0tLS0t";
    
    if(csrPEM != null) {
   String input = csrPEM.replace("-----BEGIN CERTIFICATE REQUEST-----", "")
            .replace("-----END CERTIFICATE REQUEST-----", "")
            .replace("-----BEGIN NEW CERTIFICATE REQUEST-----", "")
            .replace("-----END NEW CERTIFICATE REQUEST-----", "");

    try {
    String	csrinput = input.trim();

   String inputdata = csrinput.replaceAll("\\s+", "");
   
   if (!input.isEmpty()) {
	   String str = "-----BEGIN CERTIFICATE REQUEST-----\n" + inputdata + "\n-----END CERTIFICATE REQUEST-----\n";
       InputStream stream = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
       PKCS10CertificationRequest csr = convertPemToPKCS10CertificationRequest(stream);
       if (csr != null) {
    	   byte[] csrinbytes = Base64.getDecoder().decode(inputdata);
    	   ByteArrayInputStream bIn = new ByteArrayInputStream(csrinbytes);
    	   ASN1InputStream aIn = new ASN1InputStream(bIn);
           ASN1Sequence seq = (ASN1Sequence) aIn.readObject().toASN1Primitive();
           PemObject publickey = new PemObject("PUBLIC KEY", csr.getSubjectPublicKeyInfo().getEncoded());
           JcaPKCS10CertificationRequest jcaReq = new JcaPKCS10CertificationRequest(csr).setProvider(bcProvider);
           PublicKey pubkey = jcaReq.getPublicKey();
        String  algo = pubkey.getAlgorithm();
           String subject = csr.getSubject().toString();
           subjectDNDetailsPFX.add(subject);
           
       }
	   
   }
    
    } catch (Exception e) {
    	throw new CommonException(ErrorCodes.SUBS0005.getCode(), ErrorCodes.SUBS0005.getDescription(), e);
    }
    } 
    
    
    
      if (!subjectDNDetailsPFX.isEmpty()) {
    	  
           subjectDnDetailsStrPFX =String.join("<br>", subjectDNDetailsPFX);
      }
	  
      return subjectDnDetailsStrPFX;
	}catch (Exception e) {
		throw new CommonException(ErrorCodes.SUBS0005.getCode(), ErrorCodes.SUBS0005.getDescription(), e);
	}
}
	
public static PKCS10CertificationRequest convertPemToPKCS10CertificationRequest(InputStream pem) throws IOException, Exception {
    Security.addProvider(bcProvider);
    PKCS10CertificationRequest csr = null;
    ByteArrayInputStream inputptream = null;
    try {
        inputptream = (ByteArrayInputStream) pem;
        Reader pemReader = new BufferedReader(new InputStreamReader(inputptream));
        try (PEMParser pemParser = new PEMParser(pemReader)) {
            Object keyPair = pemParser.readObject();
            csr = (PKCS10CertificationRequest) keyPair;
        } catch (Exception e) {
        	throw new CommonException(ErrorCodes.SUBS0005.getCode(), ErrorCodes.SUBS0005.getDescription(), e);
        }
    } catch (Exception e) {
    	throw new CommonException(ErrorCodes.SUBS0005.getCode(), ErrorCodes.SUBS0005.getDescription(), e);
    }
    return csr;
}
	
	
	
	
	

	

	
	
}
