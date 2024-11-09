package com.emudhra.emra.subscriber.entity.master;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Table(name = "tbl_mas_fields")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class MasFieldList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "field_name")
	private String fieldName;
	@Column(name = "display_name")
	private String displayName;
	@Column(name = "is_mandatory_for_product")
	private Integer isMandatoryForProduct;
	@Column(name = "is_default")
	private Integer isDefault;
	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_date")
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "created_ip")
	private String createdIp;
	@Column(name = "oid")
	private String oid;
	@Column(name = "updated_date")
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@CreationTimestamp
	private LocalDateTime updatedDate;

	@Column(name = "updated_by")
	private Long updatedBy;
	@Column(name = "updated_ip")
	private String updatedIp;
	@Column(name = "is_active")
	private Integer isActive;
	@Column(name = "is_ssl")
	private Integer isSSL;
	@Column(name = "alphabets_validation")
	private Integer alphabetsValidation;
	@Column(name = "numbers_validation")
	private Integer numbersValidation;
	@Column(name = "specialCharacters_validation")
	private Integer specialCharactersValidation;

	public MasFieldList(int id, Integer isMandatoryForProduct, Integer isDefault, Integer isActive,
			String displayName) {
		super();
		this.id = id;
		// this.fieldName = fieldName;
		this.displayName = displayName;
		this.isMandatoryForProduct = isMandatoryForProduct;
		this.isDefault = isDefault;
		this.isActive = isActive;
	}
	
	

	public String getOid() {
		return oid;
	}



	public void setOid(String oid) {
		this.oid = oid;
	}



	public MasFieldList() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getIsMandatoryForProduct() {
		return isMandatoryForProduct;
	}

	public void setIsMandatoryForProduct(Integer isMandatoryForProduct) {
		this.isMandatoryForProduct = isMandatoryForProduct;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
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

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedIp() {
		return updatedIp;
	}

	public void setUpdatedIp(String updatedIp) {
		this.updatedIp = updatedIp;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getIsSSL() {
		return isSSL;
	}

	public void setIsSSL(Integer isSSL) {
		this.isSSL = isSSL;
	}

	public Integer getAlphabetsValidation() {
		return alphabetsValidation;
	}

	public void setAlphabetsValidation(Integer alphabetsValidation) {
		this.alphabetsValidation = alphabetsValidation;
	}

	public Integer getNumbersValidation() {
		return numbersValidation;
	}

	public void setNumbersValidation(Integer numbersValidation) {
		this.numbersValidation = numbersValidation;
	}

	public Integer getSpecialCharactersValidation() {
		return specialCharactersValidation;
	}

	public void setSpecialCharactersValidation(Integer specialCharactersValidation) {
		this.specialCharactersValidation = specialCharactersValidation;
	}

	public MasFieldList(int id, String fieldName, String displayName, Integer isMandatoryForProduct, Integer isDefault,
			Long createdBy, LocalDateTime createdDate, String createdIp, LocalDateTime updatedDate, Long updatedBy,
			String updatedIp, Integer isActive, Integer isSSL, Integer alphabetsValidation, Integer numbersValidation,
			Integer specialCharactersValidation) {
		super();
		this.id = id;
		this.fieldName = fieldName;
		this.displayName = fieldName;
		this.isMandatoryForProduct = isMandatoryForProduct;
		this.isDefault = isDefault;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.createdIp = createdIp;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.updatedIp = updatedIp;
		this.isActive = isActive;
		this.isSSL = isSSL;
		this.alphabetsValidation = alphabetsValidation;
		this.numbersValidation = numbersValidation;
		this.specialCharactersValidation = specialCharactersValidation;
	}

}
