package com.emudhra.emra.subscriber.enums;

public enum Status {
	ACTIVE(1,"active"),INACTIVE(0,"inactive");
	
	private final int StatusEnum;
	private final String status;

	Status(int StatusEnum, String status) {
		this.StatusEnum = StatusEnum;
		this.status=status;
	}

	public int getStatusId() {
		return this.StatusEnum;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	

}
