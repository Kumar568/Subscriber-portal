package com.emudhra.emra.subscriber.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.emudhra.emra.subscriber.business.Utilities;
import com.emudhra.emra.subscriber.dto.AdditionalFieldsData;
import com.emudhra.emra.subscriber.dto.ApplicationDto;
import com.emudhra.emra.subscriber.dto.ApplicationResponseDto;
import com.emudhra.emra.subscriber.dto.DataTableResponse;
import com.emudhra.emra.subscriber.dto.DefaultFieldsResponse;
import com.emudhra.emra.subscriber.dto.EmailBodyDto;
import com.emudhra.emra.subscriber.dto.FieldGroupDto;
import com.emudhra.emra.subscriber.dto.MapFieldGroupDto;
import com.emudhra.emra.subscriber.dto.OrderDetailsDto;
import com.emudhra.emra.subscriber.dto.StateDto;
import com.emudhra.emra.subscriber.dto.TypeAndMode;
import com.emudhra.emra.subscriber.dto.ValidationDocumentsResponse;
import com.emudhra.emra.subscriber.entity.entity.Application;
import com.emudhra.emra.subscriber.entity.entity.Customers;
import com.emudhra.emra.subscriber.entity.entity.DocumentUpload;
import com.emudhra.emra.subscriber.entity.entity.EmailVerification;
import com.emudhra.emra.subscriber.entity.entity.FieldGroup;
import com.emudhra.emra.subscriber.entity.entity.MapVeritificationChecklist;
import com.emudhra.emra.subscriber.entity.entity.OrderDetails;
import com.emudhra.emra.subscriber.entity.entity.ProductMaster;
import com.emudhra.emra.subscriber.entity.entity.UISettings;
import com.emudhra.emra.subscriber.entity.entity.VerificationChecklist;
import com.emudhra.emra.subscriber.entity.entity.VettingProfile;
import com.emudhra.emra.subscriber.entity.master.MasCountry;
import com.emudhra.emra.subscriber.enums.ApplicationStatus;
import com.emudhra.emra.subscriber.enums.EmailType;
import com.emudhra.emra.subscriber.enums.ErrorCodes;
import com.emudhra.emra.subscriber.enums.Status;
import com.emudhra.emra.subscriber.enums.UserType;
import com.emudhra.emra.subscriber.enums.VerificationMode;
import com.emudhra.emra.subscriber.enums.VerificationType;
import com.emudhra.emra.subscriber.exception.CommonException;
import com.emudhra.emra.subscriber.mapper.ApplicationMapper;
import com.emudhra.emra.subscriber.mapper.FieldGroupMapper;
import com.emudhra.emra.subscriber.mapper.OrderDetailsMapper;
import com.emudhra.emra.subscriber.mapper.TypeAndModeMapper;
import com.emudhra.emra.subscriber.service.ApplicationService;
import com.emudhra.emra.subscriber.service.CustomerService;
import com.emudhra.emra.subscriber.service.DocumentUploadService;
import com.emudhra.emra.subscriber.service.EmailVerificationService;
import com.emudhra.emra.subscriber.service.MapVerificationChecklistService;
import com.emudhra.emra.subscriber.service.MasPropertiesService;
import com.emudhra.emra.subscriber.service.OrderDetailsService;
import com.emudhra.emra.subscriber.service.UISettingsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;



@RestController
public class CustomerController {
	  @Value("${upload.documents.path}")
	    private String uploadDocumentsPath;
	  
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DocumentUploadService documentUploadService;
	@Autowired
	private Utilities utilities;
	@Autowired
	private ApplicationService applicationService;
	 @Autowired
	    private  OrderDetailsMapper orderDetailsMapper;
	 @Autowired
		private ApplicationMapper applicationMapper;
	  @Autowired
	    private FieldGroupMapper fieldGroupMapper;
		@Autowired
		private  TypeAndModeMapper typeAndModeMapper;
		@Autowired
		private MapVerificationChecklistService mapVerificationChecklistService;
		@Autowired
		private EmailVerificationService emailVerificationService;
		@Autowired
		private UISettingsService uiSettingsService;
		@Autowired
		private MasPropertiesService masPropertiesService;
		@Autowired
		private OrderDetailsService orderDetailsService;
	
	
	@GetMapping("/getmanagecertificatepage")
	public ModelAndView getview() {
		ModelAndView modelAndView =new ModelAndView("managecertificate");
		return modelAndView;
		
	}
	@GetMapping("/getmanagecertificatelist")
	public ResponseEntity<DataTableResponse<OrderDetailsDto>> getmanagecertificatelist(
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,

			HttpSession session) {
//		int isActive = Status.INACTIVE.getStatusId();
//		if (status.equals("active")) {
//			isActive = Status.ACTIVE.getStatusId();
//		} else if (status.equals("inactive")) {
//			isActive = Status.INACTIVE.getStatusId();
//
//		}
		
		Customers userObj = (Customers) session.getAttribute("user");
		Customers customers=userObj;

		Pageable pageable = PageRequest.of(pageNo, pageSize);

		DataTableResponse<OrderDetailsDto> result = customerService.getamanagecertifivatelist(customers, pageable);

		return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("/getvieworderdetail/{id}")
	public ModelAndView getviewOrderDetails(@PathVariable Long id) throws JsonMappingException, JsonProcessingException {
		try {
			 List<DocumentUpload> uploadDocuments =  new ArrayList<>();
			  EmailVerification emailVerification = null;
			  EmailVerification otpVerification  = null;
			  
	    Map<Integer, String> paymentMode = utilities.getPaymentMode();
	    OrderDetailsDto orderDetailsDto = customerService.getorderdetails(id);
	    ApplicationDto applicationDto = applicationService.getApplicationdetails(id);
	    Application applicationData = applicationService.getApplicationdetailsById(id);
	 
	    ProductMaster productMaster = new ProductMaster();
	    productMaster.setId(orderDetailsDto.getProductId());
	    VettingProfile vettingProfile = applicationService.getVettingProfileDetails(productMaster);
	    String productName = vettingProfile.getCertificateTemplate().getProductname();
	    FieldGroup fieldgroup = vettingProfile.getFieldGroup();
	    VerificationChecklist checklist = vettingProfile.getChecklist();
	    FieldGroupDto fieldGroupDto = fieldGroupMapper.mapFieldGroupToFieldGroupDto(fieldgroup);
	    
	    if(applicationData !=null) {
	    uploadDocuments = applicationData.getDocuments();
	    }
	    List<MapVeritificationChecklist> mvchecklist = mapVerificationChecklistService.getVerificationChecklist(checklist);
	    List<TypeAndMode> checklistDto = typeAndModeMapper.mapVerificationChecklistEntitylistToTypeAndModeDto(mvchecklist);
	    List<MasCountry> countryDetails = applicationService.getCountryDetails();
	    List<StateDto> states = applicationService.getStateallDetails();
	    
	    Map<String, List<Map<String, Object>>> organizedData = new HashMap<>();

	       for (TypeAndMode item : checklistDto) {
	           String typeKey = item.getTypeName();
	           if (!organizedData.containsKey(typeKey)) {
	               organizedData.put(typeKey, new ArrayList<>());
	           }

	           Map<String, Object> modeData = new HashMap<>();
	           modeData.put("modeId", item.getModeId());
	           modeData.put("modeName", item.getModeName());
	           modeData.put("typeId", item.getTypeId());
	           organizedData.get(typeKey).add(modeData);
	       }

	    
	    List<MapFieldGroupDto> enabledList = fieldGroupDto.getMapFieldGroupDtoList().stream()
	            .filter(dto -> dto.getIsEnabled() == 1)
	            .collect(Collectors.toList());

	    List<MapFieldGroupDto> mandatoryList = fieldGroupDto.getMapFieldGroupDtoList().stream()
	            .filter(dto -> dto.getIsMandatory() == 1)
	            .collect(Collectors.toList());

	    ObjectMapper objectMapper = new ObjectMapper();

	    List<AdditionalFieldsData> additionalFieldsData;

	    if (applicationDto != null && applicationDto.getAdditionalFields() != null) {
	        additionalFieldsData = objectMapper.readValue(applicationDto.getAdditionalFields(), new TypeReference<List<AdditionalFieldsData>>() {});
	    } else {
	        additionalFieldsData = new ArrayList<>();
	    }

	    List<MapFieldGroupDto> enabledadditionalField = enabledList.stream()
	            .filter(dto -> dto.getIsDefault() == 0)
	            .map(dto -> {
	                MapFieldGroupDto newDto = new MapFieldGroupDto();
	                newDto.setMasfieldid(dto.getMasfieldid());
	                newDto.setFieldName(dto.getFieldName());
	                newDto.setAlphabetsValidation(dto.getAlphabetsValidation());
	                newDto.setNumbersValidation(dto.getNumbersValidation());
	                newDto.setSpecialCharactersValidation(dto.getSpecialCharactersValidation());
	                newDto.setOid(dto.getOid());
	                newDto.setIsMandatory(dto.getIsMandatory());

	                Optional<AdditionalFieldsData> matchingData = additionalFieldsData.stream()
	                        .filter(data -> Objects.equals(data.getOid(), dto.getOid()))
	                        .findFirst();

	                if (matchingData.isPresent()) {
	                    newDto.setValue(matchingData.get().getValue());
	                } else {
	                    newDto.setValue(null);
	                }

	                return newDto;
	            })
	            .collect(Collectors.toList());

	    List<Long> enabledFieldIds = enabledList.stream()
	            .map(MapFieldGroupDto::getMasfieldid)
	            .collect(Collectors.toList());

	    
	    DefaultFieldsResponse defaultFieldsResponse=new DefaultFieldsResponse();
	    
	    defaultFieldsResponse.setDefaultEnabledFields(enabledFieldIds);
	    
	    
	    List<Long> mandatoryFieldIds = mandatoryList.stream()
	            .map(MapFieldGroupDto::getMasfieldid)
	            .collect(Collectors.toList());

	    ModelAndView modelAndView = new ModelAndView("setupmanagecertificate");
	    
	    ValidationDocumentsResponse validationDocuments = new ValidationDocumentsResponse();
		  validationDocuments.setDocuments(organizedData);
		  if(applicationDto != null) {
		  otpVerification =emailVerificationService.getEmailVerificationdata(applicationDto.getId(), VerificationMode.E_mail_OTP.getModeNum());
		 emailVerification =emailVerificationService.getEmailVerificationdata(applicationDto.getId(), VerificationMode.E_mail_Link.getModeNum());
		  }
	    if (orderDetailsDto != null || applicationDto != null || applicationData !=null || uploadDocuments !=null) {
	        modelAndView.addObject("orderDetailsDto", orderDetailsDto);
	        modelAndView.addObject("applicationData", applicationData);
	        modelAndView.addObject("applicationDto", applicationDto);
	        modelAndView.addObject("fieldGroupDto", fieldGroupDto);
	        modelAndView.addObject("enabledadditionalField", enabledadditionalField);
	        modelAndView.addObject("mandatoryFieldIds", mandatoryFieldIds);
	        modelAndView.addObject("enabledFieldIds", enabledFieldIds);
	        modelAndView.addObject("vettingProfile", vettingProfile);
	        modelAndView.addObject("paymentMode", paymentMode);
	        modelAndView.addObject("countryDetails", countryDetails);
	        modelAndView.addObject("states", states);
	        modelAndView.addObject("defaultFieldsResponse", defaultFieldsResponse);
	        modelAndView.addObject("documents", checklistDto);
	        modelAndView.addObject("otpVerification", otpVerification);
	        modelAndView.addObject("emailVerification", emailVerification);
	        modelAndView.addObject("validationDocuments", validationDocuments);
	        modelAndView.addObject("uploadDocuments", uploadDocuments);
	        modelAndView.addObject("productName", productName);
	    } else {
	       modelAndView.addObject("error", "Data not found");
	        modelAndView.setStatus(HttpStatus.NOT_FOUND);
	    }

	    return modelAndView;
	
} catch(Exception e) {
	throw new CommonException(ErrorCodes.SUBSETUP001.getCode(), ErrorCodes.SUBSETUP001.getDescription(), e);
}
	}	

	@PostMapping("/resendotp")
	public ResponseEntity<?> reSendingEmailOtp(@RequestParam String emailId,@RequestParam Long applicationId,String type, HttpSession session){
		
		try {
			UISettings us = uiSettingsService.getUISettings();
	 		String projectName = us.getProductname();
	 		EmailVerification resendMail = new EmailVerification();
	 		
			if(type.equals("1")) {
			  EmailVerification emailVerification =emailVerificationService.getEmailVerificationdata(applicationId, VerificationMode.E_mail_OTP.getModeNum());
		 
	 		 String mailsubject = "OTP Verification Link - " + projectName;
  		   int otp = customerService.getRandom();
	  			String verifingOtp = String.valueOf(otp);
	  		String encryptedotp = new String(Base64.encode(verifingOtp.getBytes()));
	  		emailVerification.setEncryptedOtp(encryptedotp);
	  		emailVerification.setIsVerified(Status.INACTIVE.getStatusId());
	  	String mailBody = customerService.getEMailBody(verifingOtp);
		Boolean result = customerService.sendEmailRA(mailBody, emailId, mailsubject);
		if (result) {
			emailVerification.setIsMailSent(Status.ACTIVE.getStatusId());
			emailVerification.setEmailSentDate(LocalDateTime.now());
		}
		resendMail = emailVerificationService.saveEmailVerification(emailVerification);
		
		return ResponseEntity.ok(resendMail);
			}
			if(type.equals("2")) {
				 EmailVerification emailVerification =emailVerificationService.getEmailVerificationdata(applicationId, VerificationMode.E_mail_Link.getModeNum());
				 String maillinksubject = "Email Verification Link - " + projectName;
      		   String token = customerService.getRandomCustomerToken();
      		   String encryptedToken =new String(Base64.encode(token.getBytes()));
      		 String appId =  String.valueOf(applicationId);
 	  	    
	    		appId =new String(Base64.encode(appId.getBytes()));
	    		emailVerification.setEmailVerificationToken(encryptedToken);
	    		emailVerification.setEmailVerificationTokenCreatedDate(LocalDateTime.now());
	    		emailVerification.setIsVerified(Status.INACTIVE.getStatusId());
	  		 String emailLinkBody = customerService.getEmailLinkBody(appId, encryptedToken);
		  	   	Boolean emailLinkSendMailResult = customerService.sendEmailRA(emailLinkBody, emailId, maillinksubject);
		  	  if(emailLinkSendMailResult) {
		  		  emailVerification.setIsMailSent(Status.ACTIVE.getStatusId());
		  		  emailVerification.setEmailSentDate(LocalDateTime.now());
		  	  }
		  	resendMail = emailVerificationService.saveEmailVerification(emailVerification);
		  	return ResponseEntity.ok(resendMail);
			}
			return ResponseEntity.ok(resendMail);
		}catch (Exception e) {
			throw new CommonException(ErrorCodes.SUBSETUP002.getCode(), ErrorCodes.SUBSETUP002.getDescription(), e);
		}
	}
	
	@PostMapping("/getvalidationdocuments")
	public ResponseEntity<?> getValidationDocuments(@RequestParam String emailId,
            @RequestParam Long applicationId, HttpSession session, HttpServletRequest request) {
		
		try {
			 Application applicationdata = applicationService.getApplicationbyId(applicationId);
		
			 VettingProfile vettingProfile = 	 applicationdata.getVettingprofile();
			    VerificationChecklist checklist = vettingProfile.getChecklist();
			    List<MapVeritificationChecklist> mvchecklist = mapVerificationChecklistService.getVerificationChecklist(checklist);
			    List<TypeAndMode> checklistDto = typeAndModeMapper.mapVerificationChecklistEntitylistToTypeAndModeDto(mvchecklist);
			    
				   Map<Integer, List<Map<String, Object>>> organizedData = new HashMap<>();

			       for (TypeAndMode item : checklistDto) {
			           Integer typeKey = item.getTypeId();
			           if (!organizedData.containsKey(typeKey)) {
			               organizedData.put(typeKey, new ArrayList<>());
			           }

			           Map<String, Object> modeData = new HashMap<>();
			           modeData.put("modeId", item.getModeId());
			           modeData.put("modeName", item.getModeName());

			           organizedData.get(typeKey).add(modeData);
			       }   
			    
			       
			    UISettings us = uiSettingsService.getUISettings();
	  	 		String projectName = us.getProductname();
	  	 		
	     
	        for(Map.Entry<Integer, List<Map<String, Object>>> entry : organizedData.entrySet()) {
	        	
	        	int verificationTypeId = entry.getKey();
	        	  List<Map<String, Object>> checkList = entry.getValue();
	        	  for (Map<String, Object> item : checkList) {
	        	        Integer verificationModeId = (Integer) item.get("modeId");
	        	    
	     
	       	 EmailVerification emailVerification = new EmailVerification();
	         Customers userObj =	(Customers)session.getAttribute("user");
		        if (userObj != null) {
		    		Long createdBy = userObj.getId();
		    		emailVerification.setCreatedby(createdBy.intValue());
		        }
	       	 Application application = new Application();
		        application.setId(applicationId);
		        emailVerification.setApplication(application);
		     
		        emailVerification.setCreatedIp(request.getRemoteAddr());  
		        
	        	   if (verificationTypeId == VerificationType.Email_Verification.getTypeNum() &&
	        	            verificationModeId == VerificationMode.E_mail_OTP.getModeNum()) {
	        		   
	        		    boolean otpExistAppId =emailVerificationService.isExistApplicationId(applicationId, VerificationMode.E_mail_OTP.getModeNum());
	       			
	       			if(!otpExistAppId) {
	        		   String mailsubject = "OTP Verification Link - " + projectName;
	        		   int otp = customerService.getRandom();
	   	  			String verifingOtp = String.valueOf(otp);
	   	  		String encryptedotp = new String(Base64.encode(verifingOtp.getBytes()));
	   	  	 	 
	   	  	String mailBody = customerService.getEMailBody(verifingOtp);
	   	  	emailVerification.setEncryptedOtp(encryptedotp);
	        emailVerification.setChecklistModeId(verificationModeId); 
	        		 
	    		Boolean otpSendMailResult = customerService.sendEmailRA(mailBody, emailId, mailsubject);
	    		if(otpSendMailResult) {
	    			emailVerification.setIsMailSent(Status.ACTIVE.getStatusId());
	    			emailVerification.setEmailSentDate(LocalDateTime.now());
	    		}
	    		EmailVerification mailVerification = emailVerificationService.saveEmailVerification(emailVerification);
	    		
	    		
	       			}  
	        	   
	        	   }
	        	   
	        	   if (verificationTypeId == VerificationType.Email_Verification.getTypeNum() &&
	        	            verificationModeId == VerificationMode.E_mail_Link.getModeNum()) {
	        		   
	        		  Boolean  emailLinkExistAppId =emailVerificationService.isExistApplicationId(applicationId, VerificationMode.E_mail_Link.getModeNum());
	        		   if(!emailLinkExistAppId) {
	        		   String maillinksubject = "Email Verification Link - " + projectName;
	        		   String token = customerService.getRandomCustomerToken();
	        		   String encryptedToken =new String(Base64.encode(token.getBytes()));
	        				   
	  	   	  emailVerification.setEmailVerificationToken(encryptedToken);
	  	   	  emailVerification.setEmailVerificationTokenCreatedDate(LocalDateTime.now());
	  	   	emailVerification.setChecklistModeId(verificationModeId); 
	  	    String appId =  String.valueOf(applicationId);
	  	    
	  	    		appId =new String(Base64.encode(appId.getBytes()));
	   	  		 String emailLinkBody = customerService.getEmailLinkBody(appId, encryptedToken);
	  	   	Boolean emailLinkSendMailResult = customerService.sendEmailRA(emailLinkBody, emailId, maillinksubject);
	  	  if(emailLinkSendMailResult) {
	  		  emailVerification.setIsMailSent(Status.ACTIVE.getStatusId());
	  		  emailVerification.setEmailSentDate(LocalDateTime.now());
	  	  }
	  	EmailVerification mailVerification = emailVerificationService.saveEmailVerification(emailVerification);
	        		   }
	        	   }
	        	   
	        	   
	        	  }   
	        	  
	        	  
	        }
	        return ResponseEntity.ok("Mailsent Sucessfully");
			
			    
		
	}catch (Exception e) {
		throw new CommonException(ErrorCodes.SUBSETUPV003.getCode(), ErrorCodes.SUBSETUPV003.getDescription(), e);
	}
	
	
	}
	
	@PostMapping("/uploaddocuments")
	public ResponseEntity<?> uploadValidationDocuments( @RequestPart("documents") MultipartFile[] documents,
	        @RequestParam("applicationId") Long applicationId,
	        @RequestParam("applicationNumber") String applicationNumber,
	        @RequestParam("numericIdInteger") List<Integer> numericIdIntegers,HttpSession session, HttpServletRequest request) {
		
		try {
			
				boolean isVerified =true;
				boolean allTypeIdsExist = true; 
			 Application applicationData = applicationService.getApplicationbyId(applicationId);
			 List<DocumentUpload> uploadDocuments = applicationData.getDocuments();
			 List<EmailVerification> emailVerification = applicationData.getEmailVerification();
			 VettingProfile vettingProfile = 	 applicationData.getVettingprofile();
			    VerificationChecklist checklist = vettingProfile.getChecklist();
			    List<MapVeritificationChecklist> mvchecklist = mapVerificationChecklistService.getVerificationChecklist(checklist);
			    List<TypeAndMode> checklistDto = typeAndModeMapper.mapVerificationChecklistEntitylistToTypeAndModeDto(mvchecklist);
			    
				   Map<Integer, List<Map<String, Object>>> organizedData = new HashMap<>();

			       for (TypeAndMode item : checklistDto) {
			           Integer typeKey = item.getTypeId();
			           if (!organizedData.containsKey(typeKey)) {
			               organizedData.put(typeKey, new ArrayList<>());
			           }

			           Map<String, Object> modeData = new HashMap<>();
			           modeData.put("modeId", item.getModeId());
			           modeData.put("modeName", item.getModeName());
			          

			           organizedData.get(typeKey).add(modeData);
			       }   
			    

			        for(Map.Entry<Integer, List<Map<String, Object>>> entry : organizedData.entrySet()) {
			        	
			        	Integer verificationTypeId = entry.getKey();
			        	  List<Map<String, Object>> checkList = entry.getValue();
			        	  for (Map<String, Object> item : checkList) {
			        	        Integer verificationModeId = (Integer) item.get("modeId");
			       
			       if(verificationModeId == VerificationMode.Upload_Document.getModeNum()) {
			    	    
			    	   String filePath = uploadDocumentsPath;
			           String appNumber = applicationNumber;

			           Path folderPath = Paths.get(filePath, appNumber);
			           
			           if (!Files.exists(folderPath)) {
			                Files.createDirectory(folderPath);
			                System.out.println("Folder created successfully: " + folderPath);
			            } else {
			                System.out.println("Folder already exists: " + folderPath);
			            }
			          
			           for (Integer id : numericIdIntegers) {
			        	    if (id != null && verificationTypeId.equals(id)) {
			        	        MultipartFile file = getFileBasedOnId(documents, numericIdIntegers, id);
			        	        DocumentUpload uploadDocument = documentUploadService.getDocumentUploadData(verificationTypeId,applicationId);
			        	        
			        	        if (file != null) {
			        	        	String fileName = file.getOriginalFilename();
			        	        	long fileSizeInBytes = file.getSize();
									double fileSizeInKB = (double) fileSizeInBytes / 1024;
									double fileSizeInMB = (double) fileSizeInKB / 1024;
									// Check the file size
										  if (fileSizeInMB > 5) { 
											  file.getInputStream().close();
											  return   ResponseEntity.badRequest().body("File size cannot exceed 5 MB"); 
											  }
										
										 // Check if it's a PDF and if it's encrypted
										  if (fileName.endsWith(".pdf")) {
											  if (isPdfEncrypted(file)) {
												  file.getInputStream().close();
												  return  ResponseEntity.ok("Encrypted PDF files are not allowed"); 
												  }
											  }
										 
										 

			        	        	  String uniqueFileName = customerService.generateUniqueFileName();
				        	            String documentHash = customerService.generateDocumentHash(uniqueFileName.getBytes());
				        	            String hashDocument = documentHash + getFileExtension(file.getOriginalFilename()).toString();
				        	            String fileGeneratedPath = folderPath.resolve(documentHash + getFileExtension(file.getOriginalFilename())).toString();
			        	        	 if(uploadDocument !=null) {
			        	        		
			        	        		 String existingDocumentHash = uploadDocument.getDocumenthash();
			        	        		 if (existingDocumentHash != null) {
			        	        			 Path existingFilePath = folderPath.resolve(existingDocumentHash);
			        	        			 if (Files.exists(existingFilePath)) {
			        	                         try {
			        	                             Files.delete(existingFilePath);
			        	                             System.out.println("Existing file deleted successfully");
			        	                         } catch (IOException e) {
			        	                             e.printStackTrace();
			        	                             // Handle deletion error as needed
			        	                         }
			        	        		 }
			        	        		 }
			        	        		 file.transferTo(new File(fileGeneratedPath));
			        	        		 Application application = new Application();
			        	        		 application.setId(applicationId);
					        	                uploadDocument.setApplication(application);
					        	                uploadDocument.setDocumenthash(hashDocument);
					        	                uploadDocument.setType(verificationTypeId);
					        	                uploadDocument.setUpdatedDate(LocalDateTime.now());
					        	                uploadDocument.setUpdatedIp(request.getRemoteAddr());
					        	                Customers userObj =	(Customers)session.getAttribute("user");
					        			        if (userObj != null) {
					        			    		Long updatedBy = userObj.getId();
					        			    		uploadDocument.setUpdatedBy(updatedBy);
					        			    		}
					        			        DocumentUpload documentUpload = documentUploadService.saveDocumnetUpload(uploadDocument);
					        			        break; 
				        	            }
			        	        	 else {
			        	          
			        	        		 file.transferTo(new File(fileGeneratedPath));
			        	                DocumentUpload docUpload = new DocumentUpload();
			        	                Application application = new Application();
			        	        		 application.setId(applicationId);
			        	                docUpload.setApplication(application);
			        	                docUpload.setDocumenthash(hashDocument);
			        	                docUpload.setType(verificationTypeId);
			        	                docUpload.setCreatedIp(request.getRemoteAddr());
			        	                Customers userObj =	(Customers)session.getAttribute("user");
			        			        if (userObj != null) {
			        			    		Long createdBy = userObj.getId();
			        			    		docUpload.setCreatedBy(createdBy);
			        			    		}
			        			        DocumentUpload documentUpload = documentUploadService.saveDocumnetUpload(docUpload);
			        	                break; 
			        	        	 }
			        	        
			        	        
			        	        }
			        	    }
			           
			           
			        	  }
			        }
			      
			       if(verificationModeId == VerificationMode.Upload_Document.getModeNum()) {
			       boolean typeIdExists = uploadDocuments.stream()
			               .anyMatch(uploadDocument -> verificationTypeId.equals(uploadDocument.getType()));
			       
			       if (!typeIdExists) {
			           allTypeIdsExist = false;
			       }
			       }
			        	  }  
			        	  if(verificationTypeId == VerificationType.Email_Verification.getTypeNum()) {
					    	    isVerified = emailVerification.stream()
					    		        .allMatch(verification -> verification.getIsVerified() == 1); 
					    	   
					       }  
			        	  
			        } 
			        if(isVerified == true && allTypeIdsExist == true &&
			        		(applicationData.getStatus() == ApplicationStatus.CERTIFICATE_DETAILS_COMPLETED.getValue())) {
			        
				    	   applicationData.setStatus(ApplicationStatus.VALIDATION_DOCUMENT_COMPLETED.getValue());
				    	   Application application = applicationService.saveApplication(applicationData);
			        }
			        
			          return ResponseEntity.ok(uploadDocuments);
			        }	
		catch (Exception e) {
			throw new CommonException(ErrorCodes.SUBSETUPV003.getCode(), ErrorCodes.SUBSETUPV003.getDescription(), e);
		}
		
		
		
	}
		
		
		

		

	private String getFileExtension(String fileName) {
	    int lastDotIndex = fileName.lastIndexOf('.');
	    return (lastDotIndex == -1) ? "" : fileName.substring(lastDotIndex);
	}

	private MultipartFile getFileBasedOnId(MultipartFile[] documents, List<Integer> numericIdIntegers, Integer targetId) {
	    for (int i = 0; i < numericIdIntegers.size(); i++) {
	        Integer id = numericIdIntegers.get(i);
	        if (id != null && id.equals(targetId)) {
	            return documents[i];
	        }
	    }
	    return null; // Return null if no matching ID is found
	}

	private boolean isPdfEncrypted(MultipartFile file) {

		try {
	       
			 PdfReader pdfReader = new PdfReader(file.getInputStream());
			 PdfReaderContentParser parser = new PdfReaderContentParser(pdfReader);
	       
		     pdfReader.close();

	        return false;
	    } catch (InvalidPasswordException e) {
	      
	        return true;
	    } catch (IOException e) {
	    
	        e.printStackTrace();
	        return true;
	    }
	}
	

@GetMapping("getdownloadandviewdocuments")
public ResponseEntity<byte[]> getDownloadAndViewDocuments(
        @RequestParam String applicationNumber,
        @RequestParam Long applicationId,
        @RequestParam int typeId,
        @RequestParam(required = false) boolean view,
        HttpServletRequest request) {

    DocumentUpload docupload = documentUploadService.getDocumentUploadData(typeId, applicationId);
    String fileName = docupload.getDocumenthash();
    String directoryPath = uploadDocumentsPath;
    Path filePath = Paths.get(directoryPath, applicationNumber, fileName);

    try {
        byte[] fileContent = Files.readAllBytes(filePath);

        // Try to determine file's content type
        String contentType = request.getServletContext().getMimeType(filePath.toString());

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        String contentDisposition = view ? "inline" : "attachment";
        // Use ResponseEntity.ok() to set status, headers, and body
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition + "; filename=\"" + getFilenameFromHeader(request) + "\"")
                .body(fileContent);

    } catch (IOException ex) {
        // Handle exceptions, e.g., file not found, permission issues
        ex.printStackTrace();
        return ResponseEntity.notFound().build(); // Or return an appropriate error response
    }
}

private String getFilenameFromHeader(HttpServletRequest request) {
    String contentDisposition = request.getHeader(HttpHeaders.CONTENT_DISPOSITION);
    if (contentDisposition != null && contentDisposition.contains("filename=")) {
        int startIndex = contentDisposition.indexOf("filename=") + 10;
        int endIndex = contentDisposition.indexOf("\"", startIndex);
        return contentDisposition.substring(startIndex, endIndex);
    }
    return "downloaded-file";
}

@PostMapping("/submitForVerification")
public ResponseEntity<?> submitForVerification(@RequestParam Long applicationId, HttpSession session){
	try {
		Integer orginator =null;
		Boolean result =false;
		 Application applicationData = applicationService.getApplicationbyId(applicationId);
		 
		  orginator = applicationData.getOrderDetails().getOriginator();
		 int staus =applicationData.getStatus();
		 
		 if(staus == ApplicationStatus.VALIDATION_DOCUMENT_COMPLETED.getValue()) {
		 
		 Long applicationNumber = applicationData.getApplicationNumber();
		 String  applicationNum= String.valueOf(applicationNumber);
//		 Customers userObj =	(Customers)session.getAttribute("user");
//	        if (userObj != null) {
//	    		 orginator = userObj.getOriginator();
//	    	
//	    		}
	        if(orginator==UserType.RA_ADMIN.getUserTypeId()) {
	        	applicationData.setStatus(ApplicationStatus.RA_ADMIN_PENDING.getValue());
	        }
	        else if (orginator == UserType.SUB_RA_ADMIN.getUserTypeId()) {
	        	applicationData.setStatus(ApplicationStatus.SUB_RA_PENDING.getValue());
	        }
	        else if (orginator == UserType.RA_OPERATOR.getUserTypeId()) {
	        	applicationData.setStatus(ApplicationStatus.RA_OPERATOR_PENDING.getValue());
	        }
	        else if (orginator == UserType.SUB_RA_OPERATOR.getUserTypeId()) {
	        	applicationData.setStatus(ApplicationStatus.SUB_RA_OPERATOR_PENDING.getValue());
	        }
	        
	        Application saveApplication = applicationService.saveApplication(applicationData);
	        
	        if(saveApplication != null) {
	        	String emailId = saveApplication.getOrderDetails().getCustomers().getEmailId();
	        	 Map<String, String>   properties = masPropertiesService.getMasPropertiesData();
	        	  UISettings us = uiSettingsService.getUISettings();
		  	 		String projectName = us.getProductname();
		  	 		String emailFooterOne = us.getFootercontent();
		  			String emailFooterTwo = us.getFooterleftcontent();
	        	 
		  	 	String  mailsubject = "Setup Completed - " + projectName;
		  	 	EmailBodyDto mailDto = utilities.getMailBody(EmailType.SETUP_CERTIFICATE_LINK.getCode());
		  	 	String mailBodyContent = mailDto.getEmailBody();
		  	  String mailbody =customerService.readFile("templates/emailtemplate.html");
		  	mailbody = mailbody.replaceAll("##MAILBODY##", mailBodyContent);
		  	 mailbody = mailbody.replaceAll("##applicationNumber##", applicationNum);
             mailbody = mailbody.replaceAll("##supportEmailId##", properties.get("CONTACT_SUPPORT_EMAILID"));
 			mailbody = mailbody.replaceAll("##productName##",		projectName);
 			mailbody = mailbody.replaceAll("##emailFooterOne##", emailFooterOne);
 			mailbody = mailbody.replaceAll("##emailFooterTwo##", emailFooterTwo);
 			 
 			 String subjectDnDetails = customerService.subjectDnDetails(applicationNumber, session);
 			mailbody = mailbody.replaceAll("##subjectDnDetails##", subjectDnDetails);
 			
 			result = customerService.sendEmailRA(mailbody, emailId, mailsubject);
             if(result) {
            	 OrderDetails ordDetails = applicationData.getOrderDetails();
            	 ordDetails.setStatus(ApplicationStatus.CERTIFICATE_DETAILS_COMPLETED.getValue());
            	 OrderDetails orderDetails =orderDetailsService.SaveOrderDetails(ordDetails);
             }
		  	  
	        }
	        return ResponseEntity.ok(result);
		 }else {
			 return ResponseEntity.ok("Upload All Documnets");
		 }
			
		
	}catch(Exception e) {
		throw new CommonException(ErrorCodes.SUBSETUPV009.getCode(), ErrorCodes.SUBSETUPV009.getDescription(), e);
		
	}


}



	
	
	
	
	   
	   
	   @GetMapping("/saveapplicationdetails/{id}")
	    public ResponseEntity<OrderDetailsDto> saveapplicationdetails(@PathVariable Long id,HttpSession session) {
	        OrderDetailsDto orderDetailsDto = customerService.getorderdetails(id);
	        Customers userObj = (Customers) session.getAttribute("user");
Application application =new Application();
application.setMobileNumber(orderDetailsDto.getCustomerphoneNumber());
//application.setOrderId(orderDetailsDto.getId());
application.setCreatedby_Name(userObj.getUserName());
application.setCreatedby(userObj.getId());


	        if (orderDetailsDto != null) {
	            return new ResponseEntity<>(orderDetailsDto, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

}
