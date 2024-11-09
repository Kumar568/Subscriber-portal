package com.emudhra.emra.subscriber.entity.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.emudhra.emra.subscriber.entity.master.MasFieldList;



@Entity
@Table(name = "tbl_map_fieldgroup")
public class MapFieldGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fieldgroup_Id")
	// @JsonManagedReference
	private FieldGroup fieldGroup;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "mas_fieldgroup_Id")
	private MasFieldList masFieldList;

	@Column(name = "is_active")
	private int isActive;
	@Column(name = "is_enabled")
	private int isEnabled;
	@Column(name = "is_mandatory")
	private int isMandatory;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		MapFieldGroup that = (MapFieldGroup) o;
		return Objects.equals(fieldGroup, that.fieldGroup) && Objects.equals(masFieldList, that.masFieldList);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fieldGroup, masFieldList);
	}

	public MapFieldGroup() {

	}

	public FieldGroup getFieldGroup() {
		return fieldGroup;
	}

	public void setFieldGroup(FieldGroup fieldGroup) {
		this.fieldGroup = fieldGroup;
	}

	public MasFieldList getMasFieldList() {
		return masFieldList;
	}

	public void setMasFieldList(MasFieldList masFieldList) {
		this.masFieldList = masFieldList;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}

	public int getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(int isMandatory) {
		this.isMandatory = isMandatory;
	}

	public MapFieldGroup(long id, FieldGroup fieldGroup, MasFieldList masFieldList, int masFieldSlNo,
			LocalDateTime createddate, int createdby, String createdbyname, String createdip, LocalDateTime updatedDate,
			int updatedBy, String updatedIp, int isActive, int isEnabled, int isMandatory) {
		super();

		this.fieldGroup = fieldGroup;
		this.masFieldList = masFieldList;

		this.isActive = isActive;
		this.isEnabled = isEnabled;
		this.isMandatory = isMandatory;
	}

}
