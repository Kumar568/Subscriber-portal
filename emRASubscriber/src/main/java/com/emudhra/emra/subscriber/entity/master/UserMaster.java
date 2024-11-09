package com.emudhra.emra.subscriber.entity.master;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;


import com.emudhra.emra.subscriber.entity.entity.OrganizationDetails;
import com.emudhra.emra.subscriber.entity.entity.UserMapPermission;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "tbl_usermaster")
public class UserMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@NotEmpty(message = "fullName is required")
	@Column(name = "full_name", nullable = true, length = 30)
	private String fullName;

	@NotNull
	@NotEmpty(message = "LoginId is unique")
	@Column(name = "user_name", nullable = true, length = 30, unique = true)
	private String userName;

//	@Column(name = "user_type_id", nullable = true)
//	private int userType;

	@Column(name = "originator_id", nullable = true)
	private int originatorId;

	@NotEmpty(message = "contactNumber is required")
	@Column(name = "contact_number", nullable = true, length = 45)
	private String contactNumber;

	@NotEmpty(message = "emailId is required")
	@Column(name = "email_id", nullable = true, length = 50)
	private String emailId;

	@Column(name = "isactive", nullable = true)
	private int isActive;

	@Column(name = "created_date", nullable = false)
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "createdip", nullable = true, length = 30)
	private String createdIp;

	@Column(name = "createdby", nullable = true)
	private int createdBy;
	
	@Column(name = "createdby_name", nullable = true)
	private String createdByName;

	@Column(name = "updated_date", nullable = true)
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	private LocalDateTime updatedDate;
	
	@Column(name = "updatedip", nullable = true, length = 30)
	private String updatedIp;

	@Column(name = "updatedby", nullable = true)
	private int updatedBy;

	@Column(name = "permission_id", nullable = true)
	private int permissionId;

	@Lob
	@Column(name = "certificate_data", nullable = true)
	private String certificateData;

	@Column(name = "dateofbirth", nullable = true)
	private Date dateOfBirth;

	@Column(name = "certificate_expiry_date", nullable = true)
	private LocalDateTime certificateExpiryDate;

	@Column(name = "certificate_serial_number", nullable = true, length = 100)
	private String certificateSerialNumber;

	@Column(name = "gender", nullable = true, length = 45)
	private String gender;

	@Column(name = "alternative_contact", nullable = true, length = 30)
	private String alternativeContact;

	@Column(name = "address", nullable = true, length = 200)
	private String address;

	@Column(name = "locality", nullable = true, length = 150)
	private String locality;
	@NotEmpty(message = "postalCode is required")
	@Column(name = "postal_code", nullable = true, length = 15)
	private String postalCode;

//	@Column(name = "country_id",nullable = true, length = 6)
//	private String countryId;
	@NotEmpty(message = "stateName is required")
	@Column(name = "state_name", nullable = true, length = 150)
	private String stateName;

	@NotEmpty(message = "city is required")
	@Column(name = "city", nullable = true, length = 45)
	private String city;

	@Column(name = "contactno_isdcode", nullable = true)
	private int contactnoIsdcode;

	@Column(name = "alternative_contact_isdcode", nullable = true)
	private int alternativeContactIsdcode;

	@Column(name = "common_operator", nullable = true)
	private int commonOperator;

	@Column(name = "landline_number", nullable = true, length = 30)
	private String landlineNumber;

//	@Column(name = "state_id", nullable = false)
//	private int stateId;

	@Column(name = "country_name", nullable = false, length = 60)
	private String countryName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_type_id", referencedColumnName = "id")
	@JsonBackReference
	private MasUserType masUserType;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	private MasCountry masCountry;

	@OneToOne
	@JoinColumn(name = "state_id", referencedColumnName = "id")
	private MasState masState;

//	@OneToMany(mappedBy = "usermaster", cascade = CascadeType.ALL)
//	@JsonManagedReference
//	private List<UserPermission> userPermissions;
	
	@ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id" ,nullable = true)
    @JsonBackReference
    private OrganizationDetails organizationDetails;
	
	 @OneToMany(
		        mappedBy = "userMaster",
		        cascade = CascadeType.ALL
		    )
	 @JsonManagedReference
		    private List<UserMapPermission> usersPermissions = new ArrayList();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

	public int getOriginatorId() {
		return originatorId;
	}

	public void setOriginatorId(int originatorId) {
		this.originatorId = originatorId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
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

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedIp() {
		return updatedIp;
	}

	public void setUpdatedIp(String updatedIp) {
		this.updatedIp = updatedIp;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public String getCertificateData() {
		return certificateData;
	}

	public void setCertificateData(String certificateData) {
		this.certificateData = certificateData;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public LocalDateTime getCertificateExpiryDate() {
		return certificateExpiryDate;
	}

	public void setCertificateExpiryDate(LocalDateTime certificateExpiryDate) {
		this.certificateExpiryDate = certificateExpiryDate;
	}

	public String getCertificateSerialNumber() {
		return certificateSerialNumber;
	}

	public void setCertificateSerialNumber(String certificateSerialNumber) {
		this.certificateSerialNumber = certificateSerialNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAlternativeContact() {
		return alternativeContact;
	}

	public void setAlternativeContact(String alternativeContact) {
		this.alternativeContact = alternativeContact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getContactnoIsdcode() {
		return contactnoIsdcode;
	}

	public void setContactnoIsdcode(int contactnoIsdcode) {
		this.contactnoIsdcode = contactnoIsdcode;
	}

	public int getAlternativeContactIsdcode() {
		return alternativeContactIsdcode;
	}

	public void setAlternativeContactIsdcode(int alternativeContactIsdcode) {
		this.alternativeContactIsdcode = alternativeContactIsdcode;
	}

	public int getCommonOperator() {
		return commonOperator;
	}

	public void setCommonOperator(int commonOperator) {
		this.commonOperator = commonOperator;
	}

	public String getLandlineNumber() {
		return landlineNumber;
	}

	public void setLandlineNumber(String landlineNumber) {
		this.landlineNumber = landlineNumber;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public MasUserType getMasUserType() {
		return masUserType;
	}

	public void setMasUserType(MasUserType masUserType) {
		this.masUserType = masUserType;
	}

	public MasCountry getMasCountry() {
		return masCountry;
	}

	public void setMasCountry(MasCountry masCountry) {
		this.masCountry = masCountry;
	}

	public MasState getMasState() {
		return masState;
	}

	public void setMasState(MasState masState) {
		this.masState = masState;
	}


	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public OrganizationDetails getOrganizationDetails() {
		return organizationDetails;
	}

	public void setOrganizationDetails(OrganizationDetails organizationDetails) {
		this.organizationDetails = organizationDetails;
	}

	public List<UserMapPermission> getUsersPermissions() {
		return usersPermissions;
	}

	public void setUsersPermissions(List<UserMapPermission> usersPermissions) {
		this.usersPermissions = usersPermissions;
	}

	
	
	
//	public void addUserPermission(UserPermission userPermission) {
//        if (userPermissions == null) {
//            userPermissions = new ArrayList<>();
//        }
//        userPermissions.add(userPermission);
//        userPermission.setUsermaster(this);
//    }

}
