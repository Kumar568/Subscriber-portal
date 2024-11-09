package com.emudhra.emra.subscriber.dto;

import java.time.LocalDateTime;

import com.emudhra.emra.subscriber.entity.entity.ProductMaster;

public class OrderDetailsDto {
	
	private Long id;
	private Long productId;
	private int validity;
	private int validityUnit;
	private String certificatefor;
	private String validityFrequencyId;
	private int status;
	private String orderDetailsStatus;
	private String productname;
	private String description;
	private LocalDateTime createdDate;
	private String customeruserName;
	private String customeremailId;
	private String customerphoneNumber;
	private String applicationStatus;
	
	private ProductMaster productMaster;
	
	

	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public ProductMaster getProductMaster() {
		return productMaster;
	}
	public void setProductMaster(ProductMaster productMaster) {
		this.productMaster = productMaster;
	}
	public String getCustomerphoneNumber() {
		return customerphoneNumber;
	}
	public void setCustomerphoneNumber(String customerphoneNumber) {
		this.customerphoneNumber = customerphoneNumber;
	}
	public String getCustomeremailId() {
		return customeremailId;
	}
	public void setCustomeremailId(String customeremailId) {
		this.customeremailId = customeremailId;
	}
	public String getCustomeruserName() {
		return customeruserName;
	}
	public void setCustomeruserName(String customeruserName) {
		this.customeruserName = customeruserName;
	}
	public String getCertificatefor() {
		return certificatefor;
	}
	public void setCertificatefor(String certificatefor) {
		this.certificatefor = certificatefor;
	}
	public String getValidityFrequencyId() {
		return validityFrequencyId;
	}
	public void setValidityFrequencyId(String validityFrequencyId) {
		this.validityFrequencyId = validityFrequencyId;
	}
	public int getValidityUnit() {
		return validityUnit;
	}
	public void setValidityUnit(int validityUnit) {
		this.validityUnit = validityUnit;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOrderDetailsStatus() {
		return orderDetailsStatus;
	}
	public void setOrderDetailsStatus(String orderDetailsStatus) {
		this.orderDetailsStatus = orderDetailsStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public int getValidity() {
		return validity;
	}
	public void setValidity(int validity) {
		this.validity = validity;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
