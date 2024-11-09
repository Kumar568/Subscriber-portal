package com.emudhra.emra.subscriber.enums;

public enum PaymentMode {
	
	
	CHEQUE("Cheque", 1),
	    CASH("Cash", 2);
	
	private final String displayName;
    private final int value;
    
    PaymentMode(String displayName, int value) {
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
