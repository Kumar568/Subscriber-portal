package com.emudhra.emra.subscriber.enums;

public enum VerificationMode {

	Email_Based(1,"Email Based"), File_Based(2, "File Based"),DNS_Based(3,"DNS Based"),
	Upload_Document(4,"Upload Document"),Video_Recording(5, "Video Recording"),
	Out_of_Band(10,"Out-of-Band"),Outbound_SMS(12, "Outbound SMS"),E_mail_OTP(28,"E-mail OTP") ,
	E_mail_Link(29, "E-mail Link");
	

	private final int ModeNum;
	private final String ModeName;
	
	VerificationMode(int ModeNum, String ModeName) {
		this.ModeNum = ModeNum;
		this.ModeName=ModeName;
	}

	public int getModeNum() {
		return this.ModeNum;
	}
	
	public String getModeName() {
		return this.ModeName;
	}
	
}
