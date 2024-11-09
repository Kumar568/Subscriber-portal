package com.emudhra.emra.subscriber.entity.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
@Table(name="tbl_validity_master")
public class ValidityMaster {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	private int id;
	 @Column(name = "description")
	private String description;
	 @Column(name = "validity_Frequency_Id")
	private long validityFrequencyId;
	 @Column(name = "validity_Unit")
	private int validityUnit;
	
	@Column(name = "created_date")
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@CreationTimestamp
	private LocalDateTime createddate;
	@Column(name = "createdBy_Name")
	private String createdBy_Name;
	@Column(name = "created_Ip")
	private String createdIp;
	@Column(name = "is_Active")
	private int isActive;
	@Column(name = "up_datedDate")
	private LocalDateTime updatedDate;
	@Column(name = "updated_By")
	private String updatedBy;
	@Column(name = "updated_Ip")
	private String updatedIp;
	
	 @OneToMany(
		        mappedBy = "validityMaster",
		        cascade = CascadeType.ALL
		    )
	 @JsonManagedReference
		    private List<MapCeritificateValidity> validityMapping = new ArrayList<>();
	 
	 

	public List<MapCeritificateValidity> getValidityMapping() {
		return validityMapping;
	}
	public void setValidityMapping(List<MapCeritificateValidity> validityMapping) {
		this.validityMapping = validityMapping;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getValidityFrequencyId() {
		return validityFrequencyId;
	}
	public void setValidityFrequencyId(long validityFrequencyId) {
		this.validityFrequencyId = validityFrequencyId;
	}
	public int getValidityUnit() {
		return validityUnit;
	}
	public void setValidityUnit(int validityUnit) {
		this.validityUnit = validityUnit;
	}
	public LocalDateTime getCreateddate() {
		return createddate;
	}
	public void setCreateddate(LocalDateTime createddate) {
		this.createddate = createddate;
	}
	public String getCreatedBy_Name() {
		return createdBy_Name;
	}
	public void setCreatedBy_Name(String createdBy_Name) {
		this.createdBy_Name = createdBy_Name;
	}
	public String getCreatedIp() {
		return createdIp;
	}
	public void setCreatedIp(String createdIp) {
		this.createdIp = createdIp;
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
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedIp() {
		return updatedIp;
	}
	public void setUpdatedIp(String updatedIp) {
		this.updatedIp = updatedIp;
	}
	
	public ValidityMaster() {
		//super();
		// TODO Auto-generated constructor stub
	}
	public ValidityMaster(int id, String description, long validityFrequencyId, int validityUnit,
			LocalDateTime createddate, String createdBy_Name, String createdIp, int isActive, LocalDateTime updatedDate,
			String updatedBy, String updatedIp, List<MapCeritificateValidity> validityMapping) {
		super();
		this.id = id;
		this.description = description;
		this.validityFrequencyId = validityFrequencyId;
		this.validityUnit = validityUnit;
		this.createddate = createddate;
		this.createdBy_Name = createdBy_Name;
		this.createdIp = createdIp;
		this.isActive = isActive;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.updatedIp = updatedIp;
		this.validityMapping = validityMapping;
	}
	
	
	

}
