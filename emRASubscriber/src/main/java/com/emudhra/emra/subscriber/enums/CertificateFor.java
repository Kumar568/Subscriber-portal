package com.emudhra.emra.subscriber.enums;

public enum CertificateFor {
	
	INDIVIDUAL("Individual", 1),
	    ORGANIZATION("Organization", 2);
	
	private final String displayName;
    private final int value;
    
    CertificateFor(String displayName, int value) {
        this.displayName = displayName;
        this.value = value;
    }
    public String getDisplayName() {
        return displayName;
    }
    public int getValue() {
        return value;
    }
	

}
