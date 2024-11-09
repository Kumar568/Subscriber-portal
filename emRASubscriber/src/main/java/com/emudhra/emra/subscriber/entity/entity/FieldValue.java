package com.emudhra.emra.subscriber.entity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name="tbl_fieldvalues")
public class FieldValue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "field_name")
	private String fieldName; 
	@Column(name = "field_value")
	private String fieldValue;
	@Column(name = "is_mandatory")
	private int isMandatory;
	@Column(name = "is_enabled")
	private int isEnabled;
	@Column(name = "is_default")
	private int isDefault;
	@Column(name = "oid")
	private String oid;
	
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "application_id")
	 * 
	 * @JsonBackReference private Application application;
	 */





	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFieldName() {
		return fieldName;
	}


	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	public String getFieldValue() {
		return fieldValue;
	}


	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}


	public int getIsMandatory() {
		return isMandatory;
	}


	public void setIsMandatory(int isMandatory) {
		this.isMandatory = isMandatory;
	}


	public int getIsEnabled() {
		return isEnabled;
	}


	public void setIsEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}


	public int getIsDefault() {
		return isDefault;
	}


	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}


	public String getOid() {
		return oid;
	}


	public void setOid(String oid) {
		this.oid = oid;
	}


//	public Application getApplication() {
//		return application;
//	}
//
//
//	public void setApplication(Application application) {
//		this.application = application;
//	}
//	 
	 
	
}
