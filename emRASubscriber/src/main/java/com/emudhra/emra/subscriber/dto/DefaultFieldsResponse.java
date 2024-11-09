package com.emudhra.emra.subscriber.dto;

import java.util.List;

import com.emudhra.emra.subscriber.enums.DefaultFields;

public class DefaultFieldsResponse {
	
	
	private List<Long> defaultEnabledFields;
	
	//private DefaultFields[] defaultenumFields ;

	public List<Long> getDefaultEnabledFields() {
		return defaultEnabledFields;
	}

	public void setDefaultEnabledFields(List<Long> defaultEnabledFields) {
		this.defaultEnabledFields = defaultEnabledFields;
	}
	
public long getValueforGender() {
	
	return DefaultFields.GENDER.getValue();
}


public long getValueforEmailId() {
	
	return DefaultFields.EMAIL_ID.getValue();
}
public long getValueforDateOfBirth() {
	
	return DefaultFields.DATE_OF_BIRTH.getValue();
}
public long getValueforMobileNumber() {
	
	return DefaultFields.MOBILE_PHONE.getValue();
}
public long getValueforAddress() {
	
	return DefaultFields.ADDRESS.getValue();
	
}

public long getValueforCountry() {
	
	return DefaultFields.COUNTRY.getValue();
}
public long getValueforState() {
	
	return DefaultFields.STATE_PROVINCE.getValue();
}
public long getValueforCity() {
	
	return DefaultFields.TOWN_CITY_DISTRICT.getValue();
}
public long getValueforPostalCode() {
	
	return DefaultFields.POSTAL_ZIP_CODE.getValue();
}
public long getValueforPan() {
	
	return DefaultFields.PAN.getValue();
}
public long getValueforGstin() {
	
	return DefaultFields.GSTIN.getValue();
}
public long getValueforOrganizationName() {
	
	return DefaultFields.ORGANIZATION_NAME.getValue();
}
public long getValueforOrganizationUnit() {
	
	return DefaultFields.ORGANIZATION_UNIT.getValue();
}
public long getValueforIecCode() {
	
	return DefaultFields.IEC_CODE.getValue();
}
public long getValueforIpAddress() {
	
	return DefaultFields.IP_ADDRESS.getValue();
}
public long getValueforRegisteredId() {
	
	return DefaultFields.REGISTERED_ID.getValue();
}
public long getValueforLandlineNumber() {
	
	return DefaultFields.LANDLINE_NUMBER.getValue();
}
public long getValueforApplicationName() {
	
	return DefaultFields.APPLICATION_NAME.getValue();
}
public long getValueforApplicationUrl() {
	
	return DefaultFields.APPLICATION_UI.getValue();
}


}
