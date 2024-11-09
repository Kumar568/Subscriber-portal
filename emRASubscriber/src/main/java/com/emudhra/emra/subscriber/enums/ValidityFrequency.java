package com.emudhra.emra.subscriber.enums;

public enum ValidityFrequency {
	YEARS("Year(s)", 1),
	MONTHS("Month(s)", 2),
	DAYS("Day(s)", 3),
	MINUTES("Minutes", 4);
	
	private final String displayName;
    private final long value;
    
    ValidityFrequency(String displayName, long value) {
        this.displayName = displayName;
        this.value = value;
    }
    public String getDisplayName() {
        return displayName;
    }
    public long getValue() {
        return value;
    }

}
