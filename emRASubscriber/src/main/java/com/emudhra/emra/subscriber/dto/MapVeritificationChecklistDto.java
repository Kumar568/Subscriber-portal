package com.emudhra.emra.subscriber.dto;

import com.emudhra.emra.subscriber.entity.master.MasVerficationType;
import com.emudhra.emra.subscriber.entity.master.MasVerificationMode;

public class MapVeritificationChecklistDto {

	private MasVerficationType typeId;

	private MasVerificationMode modeId;

	private int isActive;

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
	
	
	
	
	
}
