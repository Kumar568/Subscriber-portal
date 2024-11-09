package com.emudhra.emra.subscriber.entity.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.emudhra.emra.subscriber.business.Utilities;
import com.emudhra.emra.subscriber.entity.master.MasIssuanceProfile;



@Entity
@Table(name="tbl_vetting_profile")
public class VettingProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="profile_name",nullable = false)
	private String profileName;
	
	@OneToOne
	@JoinColumn(name="certificate_template_id",referencedColumnName = "id")
	private ProductMaster certificateTemplate;
	
	@OneToOne
	@JoinColumn(name="verification_checklist_id", referencedColumnName = "id")
	private VerificationChecklist checklist;
	
	@OneToOne
	@JoinColumn(name="field_group_id", referencedColumnName = "id")
	private FieldGroup fieldGroup;
	
	@OneToOne(optional = true)
	@JoinColumn(name="high_risk_group_id",referencedColumnName = "id", columnDefinition = "default 0")
	private HighRiskGroup highRiskGroup;
	
	@Column(name="certificate_download_method_id")
	private int downloadMethods;
	
	@Column(name="certificate_download_method_type_id")
	private int downloadMethodType;
	
	@Column(name="auto_generate_key_generation_type_id")
	private int keyGenerationType;
	
	@Column(name="issuance_mode_id")
	private int issuanceMode;
	
	@OneToOne
	@JoinColumn(name="issuance_profile_id", referencedColumnName = "profile_id")
	private MasIssuanceProfile issuanceProfile;
	
	@Column(name="approval_level_id")
	private int approvalLevel;
	
	@Column(name="highrisk_approval_id")
	private int highApprovalLevel;
	
	@Column(name="is_editby_ra")
	private int isEditRA;
	
	@Column(name="is_active")
	private int isActive;
	
	
	@Column(name="created_by")
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	private LocalDateTime createdDate;
	
	@Column(name="created_ip")
	private String createdIp;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	private LocalDateTime updatedDate;
	
	@Column(name="updated_ip")
	private String updatedIp;
	
	@Column(name="suspended_by")
	private String suspendedBy;
	
	@Column(name="suspended_date")
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	private LocalDateTime suspenedDate;
	
	@Column(name="suspended_ip")
	private String suspendedIp;
	
	@Column(name="activated_by")
	private String activatedBy;
	
	@Column(name="activated_date")
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	private LocalDateTime activatedDate;
	
	@Column(name="activated_ip")
	private String activatedIp;
	
//	@OneToMany(mappedBy = "vettingprofile")
//    private List<Application> applicationdren = new ArrayList<>();
	
	
	



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public ProductMaster getCertificateTemplate() {
		return certificateTemplate;
	}

	public void setCertificateTemplate(ProductMaster certificateTemplate) {
		this.certificateTemplate = certificateTemplate;
	}

	public VerificationChecklist getChecklist() {
		return checklist;
	}

	public void setChecklist(VerificationChecklist checklist) {
		this.checklist = checklist;
	}

	public FieldGroup getFieldGroup() {
		return fieldGroup;
	}

	public void setFieldGroup(FieldGroup fieldGroup) {
		this.fieldGroup = fieldGroup;
	}

	public HighRiskGroup getHighRiskGroup() {
		return highRiskGroup;
	}

	public void setHighRiskGroup(HighRiskGroup highRiskGroup) {
		this.highRiskGroup = highRiskGroup;
	}

	public int getDownloadMethods() {
		return downloadMethods;
	}

	public void setDownloadMethods(int downloadMethods) {
		this.downloadMethods = downloadMethods;
	}

	public int getDownloadMethodType() {
		return downloadMethodType;
	}

	public void setDownloadMethodType(int downloadMethodType) {
		this.downloadMethodType = downloadMethodType;
	}

	public int getKeyGenerationType() {
		return keyGenerationType;
	}

	public void setKeyGenerationType(int keyGenerationType) {
		this.keyGenerationType = keyGenerationType;
	}

	public int getIssuanceMode() {
		return issuanceMode;
	}

	public void setIssuanceMode(int issuanceMode) {
		this.issuanceMode = issuanceMode;
	}

	public MasIssuanceProfile getIssuanceProfile() {
		return issuanceProfile;
	}

	public void setIssuanceProfile(MasIssuanceProfile issuanceProfile) {
		this.issuanceProfile = issuanceProfile;
	}

	public int getApprovalLevel() {
		return approvalLevel;
	}

	public void setApprovalLevel(int approvalLevel) {
		this.approvalLevel = approvalLevel;
	}

	public int getHighApprovalLevel() {
		return highApprovalLevel;
	}

	public void setHighApprovalLevel(int highApprovalLevel) {
		this.highApprovalLevel = highApprovalLevel;
	}

	public int getIsEditRA() {
		return isEditRA;
	}

	public void setIsEditRA(int isEditRA) {
		this.isEditRA = isEditRA;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	@PrePersist
	public void setCreatedDate() {
		this.createdDate = new Utilities().getCurrentDateTime();
	}

	public String getCreatedIp() {
		return createdIp;
	}

	public void setCreatedIp(String createdIp) {
		this.createdIp = createdIp;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	@PreUpdate
	public void setUpdatedDate() {
		this.updatedDate = new Utilities().getCurrentDateTime();
	}

	public String getUpdatedIp() {
		return updatedIp;
	}

	public void setUpdatedIp(String updatedIp) {
		this.updatedIp = updatedIp;
	}

	public String getSuspendedBy() {
		return suspendedBy;
	}

	public void setSuspendedBy(String suspendedBy) {
		this.suspendedBy = suspendedBy;
	}

	public LocalDateTime getSuspenedDate() {
		return suspenedDate;
	}

	public void setSuspenedDate(LocalDateTime suspenedDate) {
		this.suspenedDate = suspenedDate;
	}

	public String getSuspendedIp() {
		return suspendedIp;
	}

	public void setSuspendedIp(String suspendedIp) {
		this.suspendedIp = suspendedIp;
	}

	public String getActivatedBy() {
		return activatedBy;
	}

	public void setActivatedBy(String activatedBy) {
		this.activatedBy = activatedBy;
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

	

}
