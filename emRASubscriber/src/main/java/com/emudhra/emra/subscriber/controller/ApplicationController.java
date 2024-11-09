package com.emudhra.emra.subscriber.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emudhra.emra.subscriber.config.FileUploadConfig;
import com.emudhra.emra.subscriber.dto.ApplicationDto;
import com.emudhra.emra.subscriber.dto.ApplicationResponseDto;
import com.emudhra.emra.subscriber.dto.FieldValueDTO;
import com.emudhra.emra.subscriber.dto.OrderDetailsDto;
import com.emudhra.emra.subscriber.dto.StateDto;
import com.emudhra.emra.subscriber.entity.entity.Application;
import com.emudhra.emra.subscriber.entity.entity.Customers;
import com.emudhra.emra.subscriber.entity.entity.FieldValue;
import com.emudhra.emra.subscriber.entity.entity.OrderDetails;
import com.emudhra.emra.subscriber.entity.entity.ProductMaster;
import com.emudhra.emra.subscriber.entity.entity.VettingProfile;
import com.emudhra.emra.subscriber.entity.master.MasCountry;
import com.emudhra.emra.subscriber.enums.ApplicationStatus;
import com.emudhra.emra.subscriber.service.ApplicationService;
import com.emudhra.emra.subscriber.service.CustomerService;

@RestController
public class ApplicationController {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private FileUploadConfig fileUploadConfig;
	// @Value("${EMRA_SUBSCRIBER_DOCUMENTS_FILEDIRECTORY}")
	// private String fileDirectory;

	@GetMapping("state/{country_id}")
	public List<StateDto> getStateDetails(@PathVariable int country_id) {
		List<StateDto> states = applicationService.getStateDetails(country_id);

		return states;

	}

	@GetMapping("/country")
	public List<MasCountry> getALlCountry() {
		List<MasCountry> countryDetails = applicationService.getCountryDetails();

		return countryDetails;

	}

	@PostMapping("/saveapplicationdetails/{id}")
	public ResponseEntity<?> saveApplicationDetails(@PathVariable Long id, HttpSession session) {
		try {
			OrderDetailsDto orderDetailsDto = customerService.getorderdetails(id);
			OrderDetails OrderDetails = applicationService.getOrderDetailsDetails(id);
			if (orderDetailsDto != null) {
				boolean existingOrderId = applicationService.isexistingorderId(orderDetailsDto.getId());

				if (!existingOrderId) {
					ProductMaster productMaster = new ProductMaster();
					productMaster.setId(orderDetailsDto.getProductId());

					// Check if there is an existing application for the given order
					boolean existingApplication = applicationService.isexistingorderId(orderDetailsDto.getId());

					if (!existingApplication) {
						VettingProfile vettingProfile = applicationService.getVettingProfileDetails(productMaster);
						Customers userObj = (Customers) session.getAttribute("user");

						Application application = new Application();
						application.setVettingprofile(vettingProfile);
						application.setOrderDetails(OrderDetails);
						application.setMobileNumber(orderDetailsDto.getCustomerphoneNumber());
						// application.setOrderId(orderDetailsDto.getId());
						application.setCreatedby_Name(userObj.getUserName());
						application.setCreatedby(userObj.getId());
						application.setEmailId(orderDetailsDto.getCustomeremailId());
						application.setStatus(1);
						Application saveApplication = applicationService.saveApplication(application);

						ApplicationResponseDto applicationResponseDto = new ApplicationResponseDto();
						applicationResponseDto.setApplication(saveApplication);
						applicationResponseDto.setOrderDetailsDto(orderDetailsDto);

						return new ResponseEntity<>(applicationResponseDto, HttpStatus.OK);
					} else {
						return new ResponseEntity<>("An application for this order already exists",
								HttpStatus.CONFLICT);
					}
				} else {
					return new ResponseEntity<>("Order ID already exists", HttpStatus.CONFLICT);
				}
			} else {
				return new ResponseEntity<>("OrderDetails not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception or use a logging framework
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@PostMapping("/savecertificatedetails/{id}")
	public ResponseEntity<?> saveCertificateDetails(@RequestBody ApplicationDto applicationDto, @PathVariable Long id,
			HttpSession session) {

		Application existingapplication = applicationService.getApplicationbyId(id);
		Long applicationNumber = existingapplication.getApplicationNumber();
		try {

			if (existingapplication != null) {
				existingapplication.setAddress(applicationDto.getAddress());
				existingapplication.setApplicationNumber(applicationDto.getApplicationNumber());
				existingapplication.setCity(applicationDto.getCity());

				existingapplication.setCommonName(applicationDto.getCommonName());
				// existingapplication.setCountry(applicationDto.getc);
				// existingapplication.setCountryofBirth(applicationDto.getCountryofBirth());
				existingapplication.setDateOfBirth(applicationDto.getDateOfBirth());

				existingapplication.setGender(applicationDto.getGender());
				existingapplication.setGstin(applicationDto.getGstin());
				existingapplication.setIecCode(applicationDto.getIecCode());
				// existingapplication.setNationalId(applicationDto.getNationalId());
				existingapplication.setNationalId(applicationDto.getNationalId());
				existingapplication.setOrganizationName(applicationDto.getOrganizationName());
				existingapplication.setOrganizationUnit(applicationDto.getOrganizationUnit());
				existingapplication.setOuadditonalVal(applicationDto.getOuadditonalVal());
				existingapplication.setOuEmp(applicationDto.getOuEmp());
				existingapplication.setOuStaticVal(applicationDto.getOuStaticVal());
				existingapplication.setCountry(applicationDto.getCountry());
				existingapplication.setState(applicationDto.getState());
				existingapplication.setPan(applicationDto.getPan());
				existingapplication.setPostalCode(applicationDto.getPostalCode());
				existingapplication.setRegistredID(applicationDto.getRegistredID());
				existingapplication.setAmount(applicationDto.getAmount());
				existingapplication.setModeofPayment(applicationDto.getModeofPayment());
				existingapplication.setNameoftheBank(applicationDto.getNameoftheBank());
				existingapplication.setRemarks(applicationDto.getRemarks());
				existingapplication.setCountryId(applicationDto.getCountryId());
				existingapplication.setStateId(applicationDto.getStateId());
				existingapplication.setLandlineNumber(applicationDto.getLandlineNumber());
				existingapplication.setiPaddress(applicationDto.getiPaddress());
				existingapplication.setApplicationName(applicationDto.getApplicationName());
				existingapplication.setApplicationURL(applicationDto.getApplicationURL());
				existingapplication.setStatus(ApplicationStatus.CERTIFICATE_DETAILS_COMPLETED.getValue());
				existingapplication.setAdditionalFields(applicationDto.getAdditionalFields());
				existingapplication.setApplicationNumber(applicationNumber);


				Application saveApplication = applicationService.saveCertificateDetails(existingapplication);

				ApplicationResponseDto applicationResponseDto = new ApplicationResponseDto();
				applicationResponseDto.setApplication(saveApplication);

				return new ResponseEntity<>(applicationResponseDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Application not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception or use a logging framework
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/viewuploadcsr")
	@ResponseBody
	public ResponseEntity<String> uploadCsr(@RequestPart("file") MultipartFile file) {
		String csrResponseMessage;
		String csrUploadedStatus;

		String fileOutput = null;

		try {
			// Validate file
			if (file.isEmpty() || !isAllowedFileType(file.getOriginalFilename())) {
				return ResponseEntity.badRequest().body("Invalid or empty file");
			}

			// Read file content
			byte[] data = file.getBytes();
			String encodedData = Base64.encodeBase64String(data);
			String fullText = new String(data, StandardCharsets.UTF_8);

			// Process file content
			if (fullText.contains("-----BEGIN PRIVATE KEY-----")) {
				String[] csrData = fullText.split("-----BEGIN PRIVATE KEY-----");
				fullText = csrData[0].trim();
			}

			String input = fullText.replace("-----BEGIN CERTIFICATE REQUEST-----", "")
					.replace("-----END CERTIFICATE REQUEST-----", "")
					.replace("-----BEGIN NEW CERTIFICATE REQUEST-----", "")
					.replace("-----END NEW CERTIFICATE REQUEST-----", "");

			fileOutput = "-----BEGIN CERTIFICATE REQUEST-----\n" + input + "\n-----END CERTIFICATE REQUEST-----\n";
			InputStream stream = new ByteArrayInputStream(fileOutput.getBytes(StandardCharsets.UTF_8));

			// Validate CSR
			PKCS10CertificationRequest csr = convertPemToPKCS10CertificationRequest(stream);
			if (csr == null) {
				return ResponseEntity.status(400).body("Invalid CSR");
				// csrUploadedStatus = "2";
			}

		} catch (IOException e) {
			e.printStackTrace();
			csrResponseMessage = "Error processing the CSR file";
			csrUploadedStatus = "2";
		}

		return ResponseEntity.ok(fileOutput);
	}

	private boolean isAllowedFileType(String fileName) {
		String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		return "csr".equals(fileExtension);
	}

	private PKCS10CertificationRequest convertPemToPKCS10CertificationRequest(InputStream inputStream) {
		try (Reader reader = new InputStreamReader(inputStream)) {
			PEMParser pemParser = new PEMParser(reader);
			Object parsedObj = pemParser.readObject();

			if (parsedObj instanceof PKCS10CertificationRequest) {
				return (PKCS10CertificationRequest) parsedObj;
			}
		} catch (IOException e) {
			e.printStackTrace(); // Handle the exception according to your requirements
		}
		return null;
	}



	@PostMapping("/savecsrcontent/{id}")
	public ResponseEntity<String> saveCsrContent(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
		try {
			// Retrieve the existing application
			Application existingApplication = applicationService.getApplicationbyId(id);

			if (existingApplication != null) {
				// Validate if csrFile is not empty
				if (file.isEmpty()) {
					return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
				}

				// Check if the file has a CSR extension
				if (applicationService.hasCsrExtension(file)) {
					try {
						// Extract content from the uploaded CSR file
						String fileContent = new String(file.getBytes());

						// Validate the CSR content using regex pattern
						Pattern pattern = Pattern.compile(
								"-----BEGIN CERTIFICATE REQUEST-----(.*?)-----END CERTIFICATE REQUEST-----",
								Pattern.DOTALL);
						Matcher matcher = pattern.matcher(fileContent);

						if (matcher.find()) {
							// Extract content without surrounding lines
							String extractedContent = matcher.group(1).trim();
							existingApplication.setCsr(extractedContent);

							// Assuming you want to update the applicationNumber as well
							Long applicationNumber = existingApplication.getApplicationNumber();
							existingApplication.setApplicationNumber(applicationNumber);
							existingApplication.setStatus(2);
							// Save the application with updated CSR content
							Application saveApplication = applicationService.saveApplication(existingApplication);

							// Save the file to the server
							applicationService.saveUploadedFile(file);

							return new ResponseEntity<>("CSR content and file saved successfully", HttpStatus.OK);
						} else {
							return new ResponseEntity<>("Invalid CSR file format", HttpStatus.BAD_REQUEST);
						}
					} catch (IOException e) {
						return new ResponseEntity<>("Error reading CSR file", HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} else {
					// Save the file to the server
					applicationService.saveUploadedFile(file);

					return new ResponseEntity<>("File saved successfully", HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<>("Application not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception or use a logging framework
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@PostMapping("/uploadfileintolocaldir")
	public ResponseEntity<String> handleFileUpload(@RequestBody MultipartFile file) {
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("Please select a file to upload.");
		}

		try {
			// Create the upload directory if it doesn't exist
			File uploadDir = new File(fileUploadConfig.getUploadDir());
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			// Save the file to the server
			Path filePath = Paths.get(fileUploadConfig.getUploadDir(), file.getOriginalFilename());
			file.transferTo(filePath.toFile());

			return ResponseEntity.ok("File uploaded successfully.");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error uploading the file: " + e.getMessage());
		}
	}



	@PostMapping("/validateupload")
	public ResponseEntity<?> validateCsrUpload(@RequestParam("file") MultipartFile file) {
		ResponseEntity<?> responseEntity = applicationService.validateAndProcessCsr(file);

		// Extract the body from the ResponseEntity
		Object responseBody = responseEntity.getBody();

		// Create a new ResponseEntity with the HTML response and the appropriate status
		// code
		return new ResponseEntity<>(responseBody, responseEntity.getStatusCode());
	}

	@PatchMapping("/editcsr/{id}")
	public ResponseEntity<String> deleteCsr(@PathVariable Long id) {
		try {
			Application existingApplication = applicationService.getApplicationbyId(id);

			// Set CSR field to null or any default value
			existingApplication.setCsr(null); // or set it to a default value

			Application saveApplication = applicationService.saveApplication(existingApplication);

			return ResponseEntity.ok("CSR deleted successfully");
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting CSR");
		}
	}


}
