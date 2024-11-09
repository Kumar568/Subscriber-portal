package com.emudhra.emra.subscriber.enums;

public enum ApplicationStatus {
	
	

	    NOT_INITIATED("Setup pending - Personal/Applicant details not set - NOT INITIATED", 0),
	    PERSONAL_DETAILS_COMPLETED("Setup pending - Personal/Applicant details Completed", 1),
	    CERTIFICATE_DETAILS_COMPLETED("Setup Pending - Certificate details completed - (Mandatory details filled) - auto-generated CSR", 2),
	    CSR_UPLOAD_COMPLETED("Setup Pending -- Upload CSR - applications table CSR Data found (Not Null)", 2),
	    VALIDATION_DOCUMENT_COMPLETED("Setup Pending - Validation Document - completed", 3),
	    APPLICATION_DOWNLOADED("Setup Pending - Application form downloaded", 4),
	    APPLICATION_UPLOADED("Setup Pending - Application form uploaded", 5),
	    SUB_RA_OPERATOR_PENDING("Sub RA operator pending - Setup completed", 6),
	    SUB_RA_PENDING("Sub RA pending - Setup completed", 7),
	    RA_OPERATOR_PENDING("RA Operator Pending-Setup complted", 8),
	    RA_ADMIN_PENDING("RA Admin Pending-Setup complted", 9),
	    L1_CA_PENDING("L1 CA pending", 10),
	    L2_CA_PENDING("L2 CA pending", 11),
	    L1_CA_APPROVED_L1_PENDING("L1 CA approved L1 Pending", 12),
	    L2_CA_APPROVED_L2_PENDING("L2 CA approved L2 Pending", 13),
	    L1_CA_APPROVED_L2_PENDING("L1 CA approved L2 Pending", 14),
	    AUTO_APPROVAL_FINAL_CA_APPROVAL("Auto approval (RA Admin)/Final CA Approval (Based on vetting Normal approval)", 15),
	    CERTIFICATE_GENERATED("Certificate Generated", 16),
	    CERTIFICATE_DOWNLOAD("Certificate Download", 17);

	    private final String description;
	    private final int value;

	    ApplicationStatus(String description, int value) {
	        this.description = description;
	        this.value = value;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public int getValue() {
	        return value;
	    }
	}



