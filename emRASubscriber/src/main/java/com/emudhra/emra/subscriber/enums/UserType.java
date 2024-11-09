package com.emudhra.emra.subscriber.enums;

public enum UserType {

	ADMIN(1),
	CERTIFYING_AUTHORITY(2),
	RA_ADMIN(3),
	SUB_RA_ADMIN(4),
	RA_OPERATOR(5),
	SUB_RA_OPERATOR(6);
	
	private final int userTypeId;
	
	private UserType(int userTypeId) {
		this.userTypeId=userTypeId;
	}
	
	public int getUserTypeId() {
		return this.userTypeId;
	}
	
}
