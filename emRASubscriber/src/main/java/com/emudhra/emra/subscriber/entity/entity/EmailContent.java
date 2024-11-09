package com.emudhra.emra.subscriber.entity.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "tbl_emailcontent")
public class EmailContent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	

	@Column(name = "mas_email_type_sl_no", nullable = true)
	private int masEmailTypeSlNo;
	
	 @Lob
	@Column(name = "email_body", nullable = true)
	private String emailBody;
	 
	 @Column(name = "created_date", nullable = true)
		@Type(type = "org.hibernate.type.LocalDateTimeType")
		@CreationTimestamp
		private LocalDateTime createdDate;

		@Column(name = "createdip", nullable = true, length = 50)
		private String createdIp;

		@Column(name = "createdby", nullable = true)
		private Long createdBy;

		@Column(name = "updated_date", nullable = true)
		@Type(type = "org.hibernate.type.LocalDateTimeType")
		private LocalDateTime updatedDate;
		
		@Column(name = "updatedip", nullable = true, length = 50)
		private String updatedIp;

		@Column(name = "updatedby", nullable = true)
		private Long updatedBy;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public int getMasEmailTypeSlNo() {
			return masEmailTypeSlNo;
		}

		public void setMasEmailTypeSlNo(int masEmailTypeSlNo) {
			this.masEmailTypeSlNo = masEmailTypeSlNo;
		}

		public String getEmailBody() {
			return emailBody;
		}

		public void setEmailBody(String emailBody) {
			this.emailBody = emailBody;
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

		public Long getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(Long createdBy) {
			this.createdBy = createdBy;
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



}
