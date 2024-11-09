package com.emudhra.emra.subscriber.entity.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_map_highriskgroup")
public class MapHighRiskGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "mas_field_id", nullable = false)
	private int masFieldId;
	
	@Column(name = "value", nullable = false, length = 300)
	private String value;
	
	@Column(name = "created_date", nullable = true)
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "createdip", nullable = true, length = 45)
	private String createdIp;

	@Column(name = "createdby", nullable = true)
	private int createdBy;

	
	@Column(name = "isactive", nullable = true)
	private int isActive;

	@Column(name = "updated_date", nullable = true)
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	private LocalDateTime updatedDate;
	
	@Column(name = "updatedip", nullable = true, length = 45)
	private String updatedIp;

	@Column(name = "updatedby", nullable = true)
	private int updatedBy;
	
	@Column(name = "countryName", nullable = true, length = 60)
	private String CountryName;
	
	
	@ManyToOne(fetch = FetchType.LAZY)

@JoinColumn(name = "highRiskGroupId")
   @JsonBackReference
private HighRiskGroup highRiskGroup;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMasFieldId() {
		return masFieldId;
	}

	public void setMasFieldId(int masFieldId) {
		this.masFieldId = masFieldId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
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

	public HighRiskGroup getHighRiskGroup() {
		return highRiskGroup;
	}

	public void setHighRiskGroup(HighRiskGroup highRiskGroup) {
		this.highRiskGroup = highRiskGroup;
	}

	public String getCountryName() {
		return CountryName;
	}

	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
	
	
	
	
}
