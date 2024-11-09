package com.emudhra.emra.subscriber.entity.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;

import com.emudhra.emra.subscriber.config.ApplicationContextProvider;
import com.emudhra.emra.subscriber.dto.AdditionalFieldsData;
import com.emudhra.emra.subscriber.service.ApplicationService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "tbl_application")
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "application_number",unique = true)
	private Long applicationNumber;
	@Column(name = "challenge_code")
	private String challengeCode;
	//@Column(name = "order_id")
	//private Long orderId;
	@Column(name = "vetting_Id")
	private Integer vettingId;
	@Column(name = "status")
	private int status;
	@Column(name = "domain_name")
	private String domainName;
	@Lob
	@Column(name = "csr")
	private String csr;
	@Column(name = "created_by")
	private Long createdby;
	@Column(name = "createdby_name")
	private String createdby_Name ;
	@Column(name = "created_date")
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@CreationTimestamp
	private LocalDateTime createdDate;
	@Column(name = "created_ip")
	private String createdIp ;
	@Column(name = "Common_name")
	private String commonName ; 
	@Column(name = "national_Id")
	private Long nationalId;
	@Column(name = "address")
	private String	address;
	@Column(name = "application_name")
	private String applicationName;	
	@Column(name = "application_url")
	private String	applicationURL;	
	@Column(name = "country")
	private String	country	;
	@Column(name = "email_id")
	private String	emailId	;
	@Column(name = "pan")
	private String pan;
	@Column(name = "gstin")
	private String gstin;
	@Column(name = "iec_Code")
	private String iecCode;
	@Column(name = "country_of_birth")
	private String countryofBirth;
	@Column(name = "ouEmp")
	private String ouEmp;
	@Column(name = "ouStaticVal")
	private String ouStaticVal;
	@Column(name = "ouadditonalVal")
	private String ouadditonalVal;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	@Column(name = "gender")
	private String gender;
	@Column(name = "ip_address")
	private String	iPaddress;
	@Column(name = "mobile_number")
	private String	mobileNumber; 
	@Column(name = "organization_name")
	private String	organizationName;
	@Column(name = "organization_unit")
	private String	organizationUnit;	
	@Column(name = "postal_code")
	private String	postalCode;
	@Column(name = "state")
	private String	state; 
	@Column(name = "city")
	private String	city;
	@Column(name = "registred_id")
	private String registredID;
	@Column(name = "mode_of__payment")
	private String modeofPayment;
	@Column(name = "name_of_the_bank")
	private String nameoftheBank ;
	@Column(name = "amount")
	private String amount;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "country_id")
	private int countryId;
	@Column(name = "state_id")
	private Long stateId;
	
	@Column(name = "landlinenumber")
	private Long landlineNumber;
	
	
	 @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "vettingprofile_id",referencedColumnName = "id")  // To disable insert and update
	 @JsonIgnore  
	    private VettingProfile vettingprofile;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "orderdetails_id",referencedColumnName = "id")  // To disable insert and update
	 @JsonIgnore  
	    private OrderDetails orderDetails;


	
	   @Column(name = "additional_fields", columnDefinition = "TEXT")
	    private String additionalFields;





//	   @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
//	   @JsonManagedReference
//	    private List<FieldValue> fieldValue = new ArrayList<>();


		@OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
		@JsonManagedReference
		private List<EmailVerification> emailVerification;

		@OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
		@JsonManagedReference
	    private List<DocumentUpload> documents = new ArrayList<>();

	   
	   
	   
//	public List<FieldValue> getFieldValue() {
//		return fieldValue;
//	}
//
//
//
//
//
//	public void setFieldValue(List<FieldValue> fieldValue) {
//		this.fieldValue = fieldValue;
//	}





	public String getModeofPayment() {
		return modeofPayment;
	}





	public OrderDetails getOrderDetails() {
		return orderDetails;
	}





	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}





	public VettingProfile getVettingprofile() {
		return vettingprofile;
	}





	public void setVettingprofile(VettingProfile vettingprofile) {
		this.vettingprofile = vettingprofile;
	}





	public Long getLandlineNumber() {
		return landlineNumber;
	}


	public void setLandlineNumber(Long landlineNumber) {
		this.landlineNumber = landlineNumber;
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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getApplicationNumber() {
		return applicationNumber;
	}


	public void setApplicationNumber(Long applicationNumber) {
		this.applicationNumber = applicationNumber;
	}


	public String getChallengeCode() {
		return challengeCode;
	}


	public void setChallengeCode(String challengeCode) {
		this.challengeCode = challengeCode;
	}


//	public Long getOrderId() {
//		return orderId;
//	}
//
//
//	public void setOrderId(Long orderId) {
//		this.orderId = orderId;
//	}


	public Integer getVettingId() {
		return vettingId;
	}


	public void setVettingId(Integer vettingId) {
		this.vettingId = vettingId;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getDomainName() {
		return domainName;
	}


	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}


	public String getCsr() {
		return csr;
	}


	public void setCsr(String csr) {
		this.csr = csr;
	}


	public Long getCreatedby() {
		return createdby;
	}


	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}


	public String getCreatedby_Name() {
		return createdby_Name;
	}


	public void setCreatedby_Name(String createdby_Name) {
		this.createdby_Name = createdby_Name;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public String getCreatedIp() {
		return createdIp;
	}


	public void setCreatedIp(String createdIp) {
		this.createdIp = createdIp;
	}





	public String getCommonName() {
		return commonName;
	}


	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}


	public Long getNationalId() {
		return nationalId;
	}


	public void setNationalId(Long nationalId) {
		this.nationalId = nationalId;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getApplicationName() {
		return applicationName;
	}


	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}


	public String getApplicationURL() {
		return applicationURL;
	}


	public void setApplicationURL(String applicationURL) {
		this.applicationURL = applicationURL;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
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


	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getiPaddress() {
		return iPaddress;
	}


	public void setiPaddress(String iPaddress) {
		this.iPaddress = iPaddress;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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


	public String getRegistredID() {
		return registredID;
	}


	public void setRegistredID(String registredID) {
		this.registredID = registredID;
	}

	

	public List<EmailVerification> getEmailVerification() {
		return emailVerification;
	}





	public void setEmailVerification(List<EmailVerification> emailVerification) {
		this.emailVerification = emailVerification;
	}





	public List<DocumentUpload> getDocuments() {
		return documents;
	}





	public void setDocuments(List<DocumentUpload> documents) {
		this.documents = documents;
	}





	public Application() {
		//super();
		// TODO Auto-generated constructor stub
	}


	


	@PrePersist
    public void generateApplicationNumber() {
        if (applicationNumber == null) {
            // Fetch the latest applicationNumber from the service
            ApplicationService applicationService = ApplicationContextProvider.getBean(ApplicationService.class);
            Long latestApplicationNumber = applicationService.fetchLatestApplicationNumber();

            // Set applicationNumber based on the fetched value
            this.applicationNumber = (latestApplicationNumber != null) ? Math.max(latestApplicationNumber + 1, 1000) : 1000;
        }
    }


}
