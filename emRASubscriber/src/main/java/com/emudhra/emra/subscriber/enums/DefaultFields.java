package com.emudhra.emra.subscriber.enums;

public enum DefaultFields {

	GENDER("gender",2),
    EMAIL_ID("Email ID",4),
    DATE_OF_BIRTH("date of birth",3),
    MOBILE_PHONE("Mobile Phone",5),
    ADDRESS("Address",6),
    COUNTRY("Country",7),
    STATE_PROVINCE("State/Province",8),
    TOWN_CITY_DISTRICT("Town/City/District",9),
    POSTAL_ZIP_CODE("Postal/ZIP Code",10),
    PAN("PAN",11),
    GSTIN("GSTIN",12),
    ORGANIZATION_NAME("Organization Name",14),
    ORGANIZATION_UNIT("Organization Unit",15),
    IEC_CODE("IEC Code",17),
    IP_ADDRESS("IP Address",19),
    REGISTERED_ID("Registered ID",20),
    LANDLINE_NUMBER("Landline Number",65),
    APPLICATION_NAME("Application Name",66),
    APPLICATION_UI("Application UI",67);
//	OUEMP("OuEmp",48);
//	APPLICATION_UI("ouStaticVal",49);
//	APPLICATION_UI("Application UI",67);
//	

    private final String defaultName;
    private final long value;
    DefaultFields(String defaultName,long value) {
        this.defaultName = defaultName;
        this.value=value;
    }

    public String getDefaultName() {
        return defaultName;
    }
    
    public long getValue() {
        return value;
    }
}
