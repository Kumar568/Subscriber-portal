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
import org.hibernate.annotations.Type;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Table(name=" tbl_mas_issuance_profile")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class MasIssuanceProfile {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="profile_id", nullable = false)
	private int profileId;
	
	@Column(name="profile_name")
	private String profileName;
	
	@Column(name="created_date")
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	private LocalDateTime createdDate;
	
	@Column(name="is_active")
	private int isActive;
	
	@Column(name="is_auto_issuance")
	private int isAutoIssuance;
	
	@Column(name="csr_algorithm_id")
	private int csrAlgorithmId;

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsAutoIssuance() {
		return isAutoIssuance;
	}

	public void setIsAutoIssuance(int isAutoIssuance) {
		this.isAutoIssuance = isAutoIssuance;
	}

	public int getCsrAlgorithmId() {
		return csrAlgorithmId;
	}

	public void setCsrAlgorithmId(int csrAlgorithmId) {
		this.csrAlgorithmId = csrAlgorithmId;
	}
	
}
