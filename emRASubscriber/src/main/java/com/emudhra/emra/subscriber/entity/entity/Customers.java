package com.emudhra.emra.subscriber.entity.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_customers")
public class Customers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username", nullable = false, length = 100, unique = true)
	private String userName;
	
	
	@Column(name = "emailid", nullable = false, length = 100, unique = true)
	private String emailId;
	
	@Column(name = "created_date", nullable = false)
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Column(name = "updated_date", nullable = true)
	private LocalDateTime updatedDate;
	
	
	@Column(name = "createdip", nullable = true, length = 45)
	private String createdIp;
	
	@Column(name = "createdby", nullable = true)
	private Long createdBy;
	
	@Column(name = "updatedBy", nullable = true)
	private String updatedBy;
	
	@Column(name = "passwordchanged", nullable = true, length = 10)
	private String passwordChanged;
	

	@Column(name = "password", nullable = true, length = 45)
	private String password;

	@Column(name = "isactive", nullable = true)
	private byte isActive;
	
	@Column(name = "originator", nullable = false)
	private int originator;
	
	@Column(name = "organizationid", nullable = true)
	private Long organizationId;
	
	@Column(name = "phonenumber", nullable = true, length = 100)
	private String phoneNumber;
	
	@Column(name = "firstname", nullable = true, length = 100)
	private String firstName;
	
	@OneToMany(mappedBy = "customers", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<OrderDetails> orderDetails; 
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getPasswordChanged() {
		return passwordChanged;
	}

	public void setPasswordChanged(String passwordChanged) {
		this.passwordChanged = passwordChanged;
	}



	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	
	public int getOriginator() {
		return originator;
	}

	public void setOriginator(int originator) {
		this.originator = originator;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Customers() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Customers(Long id, String userName, String emailId, LocalDateTime createdDate, LocalDateTime updatedDate,
			String createdIp, Long createdBy, String updatedBy, String passwordChanged, String password, byte isActive,
			Long organizationId) {
		super();
		this.id = id;
		this.userName = userName;
		this.emailId = emailId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdIp = createdIp;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.passwordChanged = passwordChanged;
		this.password = password;
		this.isActive = isActive;
		this.organizationId = organizationId;
	}




	
	
	
	
	
	

}
