package com.emudhra.emra.subscriber.entity.entity;

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

import com.emudhra.emra.subscriber.entity.master.MasVerficationType;
import com.emudhra.emra.subscriber.entity.master.MasVerificationMode;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_map_verification_checklist")
public class MapVeritificationChecklist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id", referencedColumnName = "id")
	private MasVerficationType typeId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mode_id", referencedColumnName = "id")
	private MasVerificationMode modeId;
	
	@ManyToOne
	@JoinColumn(name = "verification_checklist_id")
	private VerificationChecklist verificationChecklistId;

	@Column(name = "is_active")
	private int isActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MasVerficationType getTypeId() {
		return typeId;
	}

	public void setTypeId(MasVerficationType typeId) {
		this.typeId = typeId;
	}

	public MasVerificationMode getModeId() {
		return modeId;
	}

	public void setModeId(MasVerificationMode modeId) {
		this.modeId = modeId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public VerificationChecklist getVerificationChecklistId() {
		return verificationChecklistId;
	}

	public void setVerificationChecklistId(VerificationChecklist verificationChecklistId) {
		this.verificationChecklistId = verificationChecklistId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(modeId, typeId, verificationChecklistId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapVeritificationChecklist other = (MapVeritificationChecklist) obj;
		return Objects.equals(modeId, other.modeId) && Objects.equals(typeId, other.typeId)
				&& Objects.equals(verificationChecklistId, other.verificationChecklistId);
	}

}
