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
@Table(name = "tbl_highriskgroup")
public class HighRiskGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name", nullable = false, length = 100, unique = true)
	private String name;
	
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
	
	
	@Column(name = "activated_date", nullable = true)
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@CreationTimestamp
	private LocalDateTime activatedDate;

	@Column(name = "activatedIp", nullable = true, length = 45)
	private String activatedIp;

	@Column(name = "activatedBy", nullable = true)
	private int activatedBy;

	@Column(name = "suspended_date", nullable = true)
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@CreationTimestamp
	private LocalDateTime suspendedDate;

	@Column(name = "suspendedIp", nullable = true, length = 45)
	private String suspendedIp;

	@Column(name = "suspendedBy", nullable = true)
	private int suspendedBy;
	
	@Column(name = "originator", nullable = true)
	private int originator;
	
	@Column(name = "createdByName", nullable = true)
	private String createdByName;
	@Column(name = "masFieldName")
	private String MasFieldName;
	
	
	 @OneToMany(
		        mappedBy = "highRiskGroup",
		        cascade = CascadeType.ALL
		    )
	 @JsonManagedReference
		    private List<MapHighRiskGroup> mapHighRiskGroup;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public LocalDateTime getActivatedDate() {
		return activatedDate;
	}

	public void setActivatedDate(LocalDateTime activatedDate) {
		this.activatedDate = activatedDate;
	}

	public String getActivatedIp() {
		return activatedIp;
	}

	public void setActivatedIp(String activatedIp) {
		this.activatedIp = activatedIp;
	}

	public int getActivatedBy() {
		return activatedBy;
	}

	public void setActivatedBy(int activatedBy) {
		this.activatedBy = activatedBy;
	}

	public LocalDateTime getSuspendedDate() {
		return suspendedDate;
	}

	public void setSuspendedDate(LocalDateTime suspendedDate) {
		this.suspendedDate = suspendedDate;
	}

	public String getSuspendedIp() {
		return suspendedIp;
	}

	public void setSuspendedIp(String suspendedIp) {
		this.suspendedIp = suspendedIp;
	}

	public int getSuspendedBy() {
		return suspendedBy;
	}

	public void setSuspendedBy(int suspendedBy) {
		this.suspendedBy = suspendedBy;
	}

	public int getOriginator() {
		return originator;
	}

	public void setOriginator(int originator) {
		this.originator = originator;
	}

	public List<MapHighRiskGroup> getMapHighRiskGroup() {
		return mapHighRiskGroup;
	}

	public void setMapHighRiskGroup(List<MapHighRiskGroup> mapHighRiskGroup) {
		this.mapHighRiskGroup = mapHighRiskGroup;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getMasFieldName() {
		return MasFieldName;
	}

	public void setMasFieldName(String masFieldName) {
		MasFieldName = masFieldName;
	}
	
	
}
