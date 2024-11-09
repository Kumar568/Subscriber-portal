package com.emudhra.emra.subscriber.enums;

public enum ErrorCodes {
	
		ADMIN001("ADMIN001","Error While Fetching Country"),
		ADMIN002("ADMIN002","Error While Fetching State"),
	
		//Admin Secondary Auth
	
		ADMINSA001("ADMINMIU001","Error While Validating Secondary Authentication"),
	
		//Internal User
		ADMINMIU001("ADMINMIU001","Error While Viewing Internal User List"),
		ADMINMIU002("ADMINMIU002","Error While Creating Internal User"),
		ADMINMIU003("ADMINMIU003","Error While Viewing Internal User"),
		ADMINMIU004("ADMINMIU004","Error While Updating Internal User"),
		ADMINMIU005("ADMINMIU005","Error While Viewing Ceritificate Info"),
		ADMINMIU006("ADMINMIU006","Error While Searching Internal User"),
		
		// High Risk
			 	ADMINHR001("ADMINHR001","Error While Creating High Risk List"),

		
		//Organization
		ADMINMR001("ADMINMR001","Error While Viewing Organization User"),
		ADMINMR002("ADMINMR002","Error While Creating Organization User"),
		ADMINMR003("ADMINMR003","Error While Viewing Organization User"),
		ADMINMR004("ADMINMR004","Error While Updating Organization User"),
		ADMINMR005("ADMINMR005","Error While Searching Organization User"),
		ADMINMR006("ADMINMR006","Error While Fetching Branch Organization"),
		
		//RA Admin
		ADMINMA001("ADMINMA001","Error While Viewing RA Admin User List"),
		ADMINMA002("ADMINMA002","Error While Creating RA Admin User"),
		ADMINMA003("ADMINMA003","Error While Viewing RA Admin User"),
		ADMINMA004("ADMINMA004","Error While Updating RA Admin User"),
		ADMINMA005("ADMINMA005","Error While Searching RA Admin User"),
		ADMINMA006("ADMINMA006","Error While Adding Proof Of Document"),
		ADMINMA007("ADMINMA007","Error While Viewing Proof Of Document"),
		ADMINMA008("ADMINMA008","Error While Viewing Operators"),
		ADMINMA009("ADMINMA009","Error While Searching Operators"),
		ADMINMA010("ADMINMA010","Error While Viewing Branches"),
		ADMINMA011("ADMINMA011","Error While Searching Branches"),
		ADMINMA012("ADMINMA012","Error While Viewing Sub-RA Admin"),
		ADMINMA013("ADMINMA013","Error While Searching Sub-RA Admin"),
		ADMINMA014("ADMINMA014","Error While Viewing Sub-RA Operators"),
		ADMINMA015("ADMINMA015","Error While Searching Sub-RA Operators"),
		
		//Field List
		ADMINFL001("ADMINFL001","Error While Viewing Field List"),
		ADMINFL002("ADMINFL002","Error While Creating Field Group"),
		ADMINFL003("ADMINFL003","Error While Updating Field Group"),
		ADMINFL004("ADMINFL004","Error While Viewing Field Group"),
		ADMINFL005("ADMINFL005","Error While Viewing Field Group List"),
		ADMINFL006("ADMINFL006","Error While Searching Field Group"),
		ADMINFL007("ADMINFL007","Error While Active Searching Field Group"),
		ADMINFL008("ADMINFL008","Error While InActive Searching Field Group"),
		ADMINFL009("ADMINFL009","Error While Viewing Fields"),
		ADMINFL010("ADMINFL010","Error While Adding Additional Fields"),
		ADMINFL011("ADMINFL011","Error While Editing Additional Fields"),
		ADMINFL012("ADMINFL012","Error While Viewing Active Additional Fields"),
		ADMINFL013("ADMINFL013","Error While Viewing InActive Additional Fields"),
		ADMINFL014("ADMINFL014","Error While Updating Default Fields Display Name"),
		ADMINFL015("ADMINFL015","Error While Suspending Field Group"),
		
		//Certificate Template 
		ADMINCM001("ADMINCM001","Error While Creating Certificate Template"),
		ADMINCM002("ADMINCM002","Error While Creating Certificate Validity"),
		ADMINCM003("ADMINCM003","Error While Updating Certificate Validity"),
		ADMINCM004("ADMINCM004","Error While Viewing Certificate Template List"),
		ADMINCM005("ADMINCM005","Error While Updating Ceritificate Template"),
		
		//Validity
		ADMINCM006("ADMIN022","Error While Creating Validity"),
		ADMINCM007("ADMIN023","Error While Viewing Validity"),
		ADMINCM008("ADMIN024","Error While Editing Validity"),
		ADMINCM009("ADMIN025","Error While Viewing Active Validity"),
		ADMINCM010("ADMIN026","Error While Viewing InActive Validity"),
	
		//Verification Checklist
		ADMINVC001("ADMINVC001","Error While Fetching Verification Type"),
		ADMINVC002("ADMINVC001","Error While Fetching Verification Mode"),
		ADMINVC003("ADMINVC001","Error While Creating Verification Checklist"),
		ADMINVC004("ADMINVC001","Error While Viewing Verification Checklist"),
		ADMINVC005("ADMINVC001","Error While Updating Verification Checklist"),
		ADMINVC006("ADMINVC001","Error While Suspending Verification Checklist"),
		ADMINVC007("ADMINVC001","Error While Cloning Verification Checklist"),
		ADMINVC008("ADMINVC001","Error While Searching Verification Checklist"),
		ADMINVC009("ADMINVC001","Error While Searching Active Verification Checklist"),
		ADMINVC010("ADMINVC001","Error While Searching InActive Verification Checklist"),
		
		//Vetting Profile
		ADMINVP001("ADMINVC001","Error While Fetching Certificate Profile"),
		ADMINVP002("ADMINVP002","Error While Fetching Verification Checklist"),
		ADMINVP003("ADMINVP003","Error While Fetching Field Group"),
		ADMINVP004("ADMINVP004","Error While Listing Vetting Profile"),
		ADMINVP005("ADMINVP005","Error While Viewing Vetting Profile"),
		ADMINVP006("ADMINVP006","Error While Editing Vetting Profile"),
		ADMINVP007("ADMINVP007","Error While Updating Vetting Profile"),
		ADMINVP008("ADMINVP008","Error While Cloning Vetting Profile"),
		
		//SMS
		ADMINSMS001("ADMINSMS001","Error While Fetching SMS Data"),
		ADMINSMS002("ADMINSMS002","Error While Updating SMS Data"),
		
		//Email
		ADMINEMAIL001("ADMINEMAIL001","Error While Fetching Email Data"),
		ADMINEMAIL002("ADMINEMAIL002","Error While Updating Email Data"),
		
		//UI Settings
		ADMINUI001("ADMINUI001","Error While Fetching UI Setttings"),
		ADMINUI002("ADMINUI002","Error While Updating UI Settings"),
	
	   //Subscriber
		
		SUBS0004("SUBS0004","Error While Sending Email"),
		SUBS0005("SUBS0005", "Error While creating MailBody content"),
	
	//generate password
	  
		SUBGP0001("SUBGP0001", "Error While Generating Password"),
		
		
		SUBSETUP001("SUBSETUP001", "Error While Setup Certificate Page"),
		SUBSETUP002("SUBSETUP002", "Error While Resend Email OTP"),
		
		SUBSETUPV003("SUBSETUPV003", "Error While Setup Certificate Page ValidationDocuments"),
		SUBSETUPV004("SUBSETUPV004","Error While Email Verification Link"),
		SUBSETUPV005("SUBSETUPV005","Error While Verifing EmailOTP"),
		SUBSETUPV006("SUBSETUPV006","Error While Generating DocumentHash for uploadDocuments"),
		SUBSETUPV007("SUBSETUPV007", "Error While Storing UploadFiles"),
		SUBSETUPV008("SUBSETUPV008", "Error While Valiation PDF File Encryption"),
		SUBSETUPV009("SUBSETUPV009", "Error While Setup Certificate Submit For Verification"),
		
	//Login Captcha
	SUBLOGIN001("SUBLOGIN001","Error While Fetching Login Captcha"),
	SUBLOGIN002("SUBLOGIN002","Error While Fetching Dashboard page"),
	SUBLOGIN003("SUBLOGIN003","Error While Sending Forgot password Mail"),
	
	SUBLOGIN004("SUBLOGIN004","Error While Sending Forgot userName Mail"),
	SUBLOGIN005("SUBLOGIN005","Error While Viewing  Reset Password Page"),
	SUBLOGIN006("SUBLOGIN006","Error While Viewing  Change Password Page"),
	SUBLOGIN007("SUBLOGIN007","Error While Reset password"),
	SUBLOGIN008("SUBLOGIN008","Error While Change password");
	
	
	
	private final String description;
	private final String code;
	

	 ErrorCodes(String code,String description) {
		this.description = description;
		this.code=code;
	}


	public String getDescription() {
		return description;
	}
	
	public String getCode() {
		return code;
	}
}
