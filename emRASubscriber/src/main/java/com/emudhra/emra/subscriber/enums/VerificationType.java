package com.emudhra.emra.subscriber.enums;

public enum VerificationType {
	Domain_Verification(1,"Domain Verification"),Identity_proof_document(2,"Identity proof document"),
	Organization_Tax_card (3,"Organization Tax card"),Telephone_Verification(4, "Telephone Verification"),
	Email_Verification(5, "Email Verification"), Address_proof_document(6,"Address proof document"),
	EV_Verification(8,"EV Verification"), National_ID_document(13,"National ID document"),Passport(14,"Passport"),
	Valid_Business_Lincense(15,"Valid Business Lincense"),Certificate_of_incorporation(16,"Certificate of incorporation"),
	Bank_Statement(17,"Bank Statement / Bank Certificate"), Registered_patnership_deed(18, "Registered patnership deed"),
	Proof_of_Authorized_Signatory(19, "Proof of Authorized Signatory"),Authorized_Signatory_ID_Proof(20,"Authorized Signatory ID Proof"),
	VAT_Registration_Certificate(21,"VAT Registration Certificate"),Authorization_letter(22,"Authorization letter"),Employee_ID_Card(23,"Employee ID Card"),Applicant_Photograph(24,"Applicant Photograph"),Undertaking_for_HSM_based_CSR_certificate_download(25,"Undertaking for HSM based CSR & certificate download"),
	Certificate_Of_URL_Registration(26,"Certificate_Of URL Registration");
	
	private final int TypeNum;
	private final String TypeName;
	
	VerificationType(int TypeNum, String TypeName) {
		this.TypeNum = TypeNum;
		this.TypeName=TypeName;
	}

	public int getTypeNum() {
		return this.TypeNum;
	}
	
	public String getTypeName() {
		return this.TypeName;
	}

}
