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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;


import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "email_verification")
public class EmailVerification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "supportingfile", length =500, nullable = true)
	private String supportingFile;
	
	@Column(name = "created_date")
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@CreationTimestamp
	private LocalDateTime createddate;
	
	@Column(name = "created_by", nullable = false)
	private int createdby;
	
	@Column(name = "createdip",length = 45, nullable = false)
	private String createdIp;
	
	@Column(name = "is_verified", nullable = false)
	private int isVerified;
	
	@Column(name = "is_mailsent", nullable = true)
	private int isMailSent;
	
	@Column(name = "encryptedotp",length = 45, nullable = true)
	private String encryptedOtp;
	
	@Column(name = "emailsentdate", nullable = true)
	private LocalDateTime emailSentDate;
	
	@Column(name = "isuploaded", nullable = true)
	private int isUploaded;
	
	@Column(name = "email_verification_token", length = 100, nullable = true)
	private String emailVerificationToken;
	
	
	@Column(name = "email_verification_token_createddate", nullable = true)
	private LocalDateTime emailVerificationTokenCreatedDate;
	
	@Column(name = "checklist_modeid", nullable = false)
	private int checklistModeId;
	
	@Column(name = "updated_date", nullable = true)
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	private LocalDateTime updatedDate;
	
	@Column(name = "updatedip", nullable = true, length = 45)
	private String updatedIp;

	@Column(name = "updatedby", nullable = true)
	private Long updatedBy;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "application_id", referencedColumnName = "id")
	@JsonBackReference(value = "emailVerification-reference")
	private Application application;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getSupportingFile() {
		return supportingFile;
	}


	public void setSupportingFile(String supportingFile) {
		this.supportingFile = supportingFile;
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


	public String getCreatedIp() {
		return createdIp;
	}


	public void setCreatedIp(String createdIp) {
		this.createdIp = createdIp;
	}


	public int getIsVerified() {
		return isVerified;
	}


	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}


	public int getIsMailSent() {
		return isMailSent;
	}


	public void setIsMailSent(int isMailSent) {
		this.isMailSent = isMailSent;
	}


	public String getEncryptedOtp() {
		return encryptedOtp;
	}


	public void setEncryptedOtp(String encryptedOtp) {
		this.encryptedOtp = encryptedOtp;
	}


	public LocalDateTime getEmailSentDate() {
		return emailSentDate;
	}


	public void setEmailSentDate(LocalDateTime emailSentDate) {
		this.emailSentDate = emailSentDate;
	}


	public int getIsUploaded() {
		return isUploaded;
	}


	public void setIsUploaded(int isUploaded) {
		this.isUploaded = isUploaded;
	}


	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}


	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}


	public LocalDateTime getEmailVerificationTokenCreatedDate() {
		return emailVerificationTokenCreatedDate;
	}


	public void setEmailVerificationTokenCreatedDate(LocalDateTime emailVerificationTokenCreatedDate) {
		this.emailVerificationTokenCreatedDate = emailVerificationTokenCreatedDate;
	}


	public int getChecklistModeId() {
		return checklistModeId;
	}


	public void setChecklistModeId(int checklistModeId) {
		this.checklistModeId = checklistModeId;
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


	public Long getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Application getApplication() {
		return application;
	}


	public void setApplication(Application application) {
		this.application = application;
	}
	
	
	
	
}
