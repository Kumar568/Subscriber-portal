package com.emudhra.emra.subscriber.dto;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationDto1 {
	
	private Long id;
	private Long orderId;
	private Integer vettingId;
	private String commonName ;
	private String applicationStatus;
	private Long applicationNumber;
	private String gender;
	private String uploadCsrStatus;
	private String	certificateDetailsStatus;	
	private String	country	;
	private String mobileNumber;
	private String emailId;
	private String address;
	private Date dateOfBirth;


	private String modeofPayment;

	private String nameoftheBank ;

	private String amount;

	private String remarks;
	
	private String state;
	
	private String city;
	private String postalCode;
	private Long nationalId;
	private String organizationName;
	private String organizationUnit;
	private String registredID;
	
	private String csr;
	
	private String pan;

	private String gstin;

	private String iecCode;

	private String countryofBirth;

	private String ouEmp;
	
	private String ouStaticVal;
	
	private String ouadditonalVal;
	
	 private String additionalFields;
	 private List<FieldValueDTO> additionalFieldsvalue;
	 
		private int countryId;

		private Long stateId;

		private Long landlineNumber;
		
		private String	iPaddress;
		private String	applicationURL;	
		
		private String applicationName;	
		
		
		
		
		
	 public List<FieldValueDTO> getAdditionalFieldsvalue() {
			return additionalFieldsvalue;
		}










		public void setAdditionalFieldsvalue(List<FieldValueDTO> additionalFieldsvalue) {
			this.additionalFieldsvalue = additionalFieldsvalue;
		}










	public List<AdditionalFieldsData> getAdditionalFieldsList() {
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            return objectMapper.readValue(additionalFields, new TypeReference<List<AdditionalFieldsData>>() {});
	        } catch (IOException e) {
	            e.printStackTrace(); // Handle the exception according to your application's error handling strategy
	            return null;
	        }
	    }
	 
	 
	 

	





	public String getUploadCsrStatus() {
		return uploadCsrStatus;
	}










	public void setUploadCsrStatus(String uploadCsrStatus) {
		this.uploadCsrStatus = uploadCsrStatus;
	}










	public String getCsr() {
		return csr;
	}




	public void setCsr(String csr) {
		this.csr = csr;
	}




	public String getCertificateDetailsStatus() {
		return certificateDetailsStatus;
	}




	public void setCertificateDetailsStatus(String certificateDetailsStatus) {
		this.certificateDetailsStatus = certificateDetailsStatus;
	}




	public Long getLandlineNumber() {
		return landlineNumber;
	}




	public void setLandlineNumber(Long landlineNumber) {
		this.landlineNumber = landlineNumber;
	}




	public String getiPaddress() {
		return iPaddress;
	}




	public void setiPaddress(String iPaddress) {
		this.iPaddress = iPaddress;
	}




	public String getApplicationURL() {
		return applicationURL;
	}




	public void setApplicationURL(String applicationURL) {
		this.applicationURL = applicationURL;
	}




	public String getApplicationName() {
		return applicationName;
	}




	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}




	public int getCountryId() {
		return countryId;
	}




	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}




	public Long getStateId() {
		return stateId;
	}




	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}




	public String getModeofPayment() {
		return modeofPayment;
	}

	public String getAdditionalFields() {
		return additionalFields;
	}

	public void setAdditionalFields(String additionalFields) {
		this.additionalFields = additionalFields;
	}

	public void setModeofPayment(String modeofPayment) {
		this.modeofPayment = modeofPayment;
	}
	public String getNameoftheBank() {
		return nameoftheBank;
	}
	public void setNameoftheBank(String nameoftheBank) {
		this.nameoftheBank = nameoftheBank;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getCountry() {
		return country;
	}




	public void setCountry(String country) {
		this.country = country;
	}




	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Long getNationalId() {
		return nationalId;
	}
	public void setNationalId(Long nationalId) {
		this.nationalId = nationalId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationUnit() {
		return organizationUnit;
	}
	public void setOrganizationUnit(String organizationUnit) {
		this.organizationUnit = organizationUnit;
	}
	public String getRegistredID() {
		return registredID;
	}
	public void setRegistredID(String registredID) {
		this.registredID = registredID;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getIecCode() {
		return iecCode;
	}
	public void setIecCode(String iecCode) {
		this.iecCode = iecCode;
	}

	public String getCountryofBirth() {
		return countryofBirth;
	}
	public void setCountryofBirth(String countryofBirth) {
		this.countryofBirth = countryofBirth;
	}
	public String getOuEmp() {
		return ouEmp;
	}
	public void setOuEmp(String ouEmp) {
		this.ouEmp = ouEmp;
	}
	public String getOuStaticVal() {
		return ouStaticVal;
	}
	public void setOuStaticVal(String ouStaticVal) {
		this.ouStaticVal = ouStaticVal;
	}
	public String getOuadditonalVal() {
		return ouadditonalVal;
	}
	public void setOuadditonalVal(String ouadditonalVal) {
		this.ouadditonalVal = ouadditonalVal;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public Long getApplicationNumber() {
		return applicationNumber;
	}
	public void setApplicationNumber(Long applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Integer getVettingId() {
		return vettingId;
	}
	public void setVettingId(Integer vettingId) {
		this.vettingId = vettingId;
	}




	public String getCommonName() {
		return commonName;
	}




	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	
	

}
