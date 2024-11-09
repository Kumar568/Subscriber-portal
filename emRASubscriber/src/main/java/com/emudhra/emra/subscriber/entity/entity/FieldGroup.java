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
@Table(name="tbl_field_group")
public class FieldGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "created_date")
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@CreationTimestamp
	private LocalDateTime createddate;
	
	@Column(name = "created_by")
	private int createdby;
	
	@Column(name = "createdby_name")
	private String createdbyname;
	
	@Column(name = "created_ip")
	private String createdip;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "activated_date")
	private LocalDateTime activatedDate;
	
	@Column(name = "activated_by")
	private int activatedBy;
	
	@Column(name = "activated_ip")
	private String activatedIp;
	
	@Column(name = "suspended_date")
	private LocalDateTime suspendeddate;
	
	@Column(name = "suspended_by")
	private int suspendedby;
	
	@Column(name = "suspended_ip")
	private String suspendedip;
	
	@Column(name = "originator")
	private int originator;
	
	@OneToMany(
	        mappedBy = "fieldGroup",
	        cascade = CascadeType.ALL
	       
	    )
	@JsonManagedReference
	    private List<MapFieldGroup> mapFieldGroup = new ArrayList();
	 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getCreateddate() {
		return createddate;
	}
	public void setCreateddate(LocalDateTime createddate) {
		this.createddate = createddate;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public String getCreatedip() {
		return createdip;
	}
	public void setCreatedip(String createdip) {
		this.createdip = createdip;
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
	public int getActivatedBy() {
		return activatedBy;
	}
	public void setActivatedBy(int activatedBy) {
		this.activatedBy = activatedBy;
	}
	public String getActivatedIp() {
		return activatedIp;
	}
	public void setActivatedIp(String activatedIp) {
		this.activatedIp = activatedIp;
	}
	public LocalDateTime getSuspendeddate() {
		return suspendeddate;
	}
	public void setSuspendeddate(LocalDateTime suspendeddate) {
		this.suspendeddate = suspendeddate;
	}
	public int getSuspendedby() {
		return suspendedby;
	}
	public void setSuspendedby(int suspendedby) {
		this.suspendedby = suspendedby;
	}
	public String getSuspendedip() {
		return suspendedip;
	}
	public void setSuspendedip(String suspendedip) {
		this.suspendedip = suspendedip;
	}
	public int getOriginator() {
		return originator;
	}
	public void setOriginator(int originator) {
		this.originator = originator;
	}



	public String getCreatedbyname() {
		return createdbyname;
	}
	public void setCreatedbyname(String createdbyname) {
		this.createdbyname = createdbyname;
	}
	
	public FieldGroup(long id, String name, LocalDateTime createddate, int createdby, String createdbyname,
			String createdip, int isActive, LocalDateTime activatedDate, int activatedBy, String activatedIp,
			LocalDateTime suspendeddate, int suspendedby, String suspendedip, int originator,
			List<MapFieldGroup> mapFieldGroup) {
		super();
		this.id = id;
		this.name = name;
		this.createddate = createddate;
		this.createdby = createdby;
		this.createdbyname = createdbyname;
		this.createdip = createdip;
		this.isActive = isActive;
		this.activatedDate = activatedDate;
		this.activatedBy = activatedBy;
		this.activatedIp = activatedIp;
		this.suspendeddate = suspendeddate;
		this.suspendedby = suspendedby;
		this.suspendedip = suspendedip;
		this.originator = originator;
		this.mapFieldGroup = mapFieldGroup;
	}
	public FieldGroup() {
		//super();
		// TODO Auto-generated constructor stub
	}
	public List<MapFieldGroup> getMapFieldGroup() {
		return mapFieldGroup;
	}
	public void setMapFieldGroup(List<MapFieldGroup> mapFieldGroup) {
		this.mapFieldGroup = mapFieldGroup;
	}
	

}
