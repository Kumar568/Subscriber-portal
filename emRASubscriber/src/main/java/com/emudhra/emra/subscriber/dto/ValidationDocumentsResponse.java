package com.emudhra.emra.subscriber.dto;

import java.util.List;
import java.util.Map;

import com.emudhra.emra.subscriber.enums.ApplicationStatus;
import com.emudhra.emra.subscriber.enums.CertificateDownloadMethodType;
import com.emudhra.emra.subscriber.enums.VerificationMode;
import com.emudhra.emra.subscriber.enums.VerificationType;

public class ValidationDocumentsResponse {

	private Map<String, List<Map<String, Object>>> documents;
	private  VerificationMode [] verificationMode = VerificationMode.values();
   
	private VerificationType [] verificationType = VerificationType.values();
	
	
	
	public Map<String, List<Map<String, Object>>> getDocuments() {
		return documents;
	}
	public void setDocuments(Map<String, List<Map<String, Object>>> documents) {
		this.documents = documents;
	}
	public VerificationMode[] getVerificationMode() {
		return verificationMode;
	}
	public void setVerificationMode(VerificationMode[] verificationMode) {
		this.verificationMode = verificationMode;
	}
	public VerificationType[] getVerificationType() {
		return verificationType;
	}
	public void setVerificationType(VerificationType[] verificationType) {
		this.verificationType = verificationType;
	}
    
	 public String getTypeNumForIdentityProof() {
	        return VerificationType.Identity_proof_document.getTypeName();
	    }

	    public String getTypeNumForAddressProof() {
	        return VerificationType.Address_proof_document.getTypeName();
	    }

	    public int getModeNumForUploadDocument() {
	        return VerificationMode.Upload_Document.getModeNum();
	    }
	    
	    public String getTypeNumForEmailVerification() {
	        return VerificationType.Email_Verification.getTypeName();
	    }
	    
	    public int getModeNumForEmailOTP() {
	    	return VerificationMode.E_mail_OTP.getModeNum();
	    }
	    
	    public int getModeNumForEmailLink() {
	    	return VerificationMode.E_mail_Link.getModeNum();
	    }
	    public int getStatusForValidationDocuments() {
	    	return ApplicationStatus.VALIDATION_DOCUMENT_COMPLETED.getValue();
	    }
	    public int getApplicationStatusForValidationDocuments() {
	    	return ApplicationStatus.CERTIFICATE_DETAILS_COMPLETED.getValue();
	    }
	    
	    public int getDownloadMethodType() {
	    	return CertificateDownloadMethodType.UPLOADCSR.getId();
	    }
	    
	    
}
