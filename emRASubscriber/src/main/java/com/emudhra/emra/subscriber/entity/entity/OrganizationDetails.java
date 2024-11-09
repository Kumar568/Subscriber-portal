package com.emudhra.emra.subscriber.entity.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import com.emudhra.emra.subscriber.entity.master.MasCountry;
import com.emudhra.emra.subscriber.entity.master.MasState;
import com.emudhra.emra.subscriber.entity.master.UserMaster;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_organization_details")
public class OrganizationDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty(message = "organization_name is required")
	@Column(name = "organization_name", nullable = false, length = 200)
	private String organizationName;

	@NotNull
	@Column(name = "organization_type_id", nullable = false)
	private int organizationTypeId;

	@ManyToOne
	@JoinColumn(name = "parent_organization_id", referencedColumnName = "id", nullable = true)
	private OrganizationDetails parentOrganization;

	@Column(name = "parentOrganizationName", nullable = true, length = 200)
	private String parentOrganizationName;

	public String getParentOrganizationName() {
		return parentOrganizationName;
	}

	public void setParentOrganizationName(String parentOrganizationName) {
		this.parentOrganizationName = parentOrganizationName;
	}

	@Column(name = "sub_orgApproval", nullable = true)
	private Integer subOrgApproval;

	public void setSubOrgApproval(Integer subOrgApproval) {
		this.subOrgApproval = subOrgApproval;
	}

	@Column(name = "contactno_isdCode", nullable = true, length = 10)
	private String contactno_isdCode;

	@NotEmpty(message = "racode is required")
	@Column(name = "ra_code", nullable = false, length = 50)
	private String raCode;

	@Column(name = "ra_type_id", nullable = false)
	private int raTypeId;

	@Column(name = "createdby_name", nullable = false)
	private String createdByName;

	@Column(name = "contact_number", nullable = true, length = 45)
	private String contactNumber;

	@NotEmpty(message = "emailId is required")
	@Column(name = "email_id", nullable = false, length = 50)
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

	@Column(name = "updated_date", nullable = true)
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	private LocalDateTime updatedDate;

	@Column(name = "updatedip", nullable = true, length = 30)
	private String updatedIp;

	@Column(name = "updatedby", nullable = true)
	private int updatedBy;

	@Column(name = "address", nullable = true, length = 200)
	private String address;

	@NotEmpty(message = "postalCode is required")
	@Column(name = "postal_code", nullable = false, length = 15)
	private String postalCode;

	@NotEmpty(message = "city is required")
	@Column(name = "city", nullable = false, length = 45)
	private String city;

	@NotEmpty(message = "stateName is required")
	@Column(name = "state_name", nullable = false, length = 150)
	private String stateName;

	@Column(name = "landline_number", nullable = true, length = 30)
	private String landlineNumber;

	@Column(name = "country_name", nullable = false, length = 60)
	private String countryName;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "state_id", referencedColumnName = "id")
	private MasState masState;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	private MasCountry masCountry;

	@OneToMany(mappedBy = "organizationDetails", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<UserMaster> userMaster;
	
	@OneToMany(mappedBy = "organizationDetails", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<OrderDetails> orderDetails;
	

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public MasState getMasState() {
		return masState;
	}

	public void setMasState(MasState masState) {
		this.masState = masState;
	}

	public MasCountry getMasCountry() {
		return masCountry;
	}

	public void setMasCountry(MasCountry masCountry) {
		this.masCountry = masCountry;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public int getOrganizationTypeId() {
		return organizationTypeId;
	}

	public void setOrganizationTypeId(int organizationTypeId) {
		this.organizationTypeId = organizationTypeId;
	}

	public OrganizationDetails getParentOrganization() {
		return parentOrganization;
	}

	public void setParentOrganization(OrganizationDetails parentOrganization) {
		this.parentOrganization = parentOrganization;
	}

//	public int getSubOrgApproval() {
//		return subOrgApproval;
//	}
//
//	public void setSubOrgApproval(int subOrgApproval) {
//		this.subOrgApproval = subOrgApproval;
//	}

	public String getContactno_isdCode() {
		return contactno_isdCode;
	}

	public void setContactno_isdCode(String contactno_isdCode) {
		this.contactno_isdCode = contactno_isdCode;
	}

	public String getRaCode() {
		return raCode;
	}

	public void setRaCode(String raCode) {
		this.raCode = raCode;
	}

	public int getRaTypeId() {
		return raTypeId;
	}

	public void setRaTypeId(int raTypeId) {
		this.raTypeId = raTypeId;
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

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
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

	public List<UserMaster> getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(List<UserMaster> userMaster) {
		this.userMaster = userMaster;
	}

	public Integer getSubOrgApproval() {
		return subOrgApproval;
	}

}
