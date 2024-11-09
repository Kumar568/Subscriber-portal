package com.emudhra.emra.subscriber.entity.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="tbl_verification_checklist")
public class VerificationChecklist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_ip")
	private String createdIp;
	
	@Column(name="updated_date")
	private LocalDateTime updatedDate;

	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_ip")
	private String updatedIp;
	
	@Column(name="is_active")
	private int isActive;
	
	@Column(name="suspended_date")
	private LocalDateTime suspendedDate;
	
	@Column(name="suspended_by")
	private String suspendedBy;
	
	@Column(name="suspened_ip")
	private String suspendedIp;
	
	@Column(name="activated_by")
	private String activatedBy;
	
	@Column(name="activated_date")
	private LocalDateTime activeatedDate;

	@Column(name="activated_ip")
	private String activatedIp;
	
	@OneToMany(mappedBy  ="verificationChecklistId", 
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<MapVeritificationChecklist> veritificationChecklists;

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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
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

	

	public LocalDateTime getActiveatedDate() {
		return activeatedDate;
	}

	public void setActiveatedDate(LocalDateTime activeatedDate) {
		this.activeatedDate = activeatedDate;
	}

	public String getActivatedIp() {
		return activatedIp;
	}

	public void setActivatedIp(String activatedIp) {
		this.activatedIp = activatedIp;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerificationChecklist other = (VerificationChecklist) obj;
		return id == other.id;
	}

	public List<MapVeritificationChecklist> getVeritificationChecklists() {
		return veritificationChecklists;
	}

	public void setVeritificationChecklists(List<MapVeritificationChecklist> veritificationChecklists) {
		this.veritificationChecklists = veritificationChecklists;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getSuspendedBy() {
		return suspendedBy;
	}

	public void setSuspendedBy(String suspendedBy) {
		this.suspendedBy = suspendedBy;
	}

	public String getActivatedBy() {
		return activatedBy;
	}

	public void setActivatedBy(String activatedBy) {
		this.activatedBy = activatedBy;
	}


}
