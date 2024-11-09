package com.emudhra.emra.subscriber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emudhra.emra.subscriber.entity.entity.DocumentUpload;
import com.emudhra.emra.subscriber.repository.DocumentUploadRepository;

@Service
public class DocumentUploadService {
@Autowired
private DocumentUploadRepository documentUploadRepository;



public  DocumentUpload saveDocumnetUpload(DocumentUpload documentUpload) {
	return documentUploadRepository.save(documentUpload);
}



public DocumentUpload getDocumentUploadData(Integer verificationTypeId, Long applicationId) {
	
	DocumentUpload docUpload = documentUploadRepository.findByTypeAndApplicationId(verificationTypeId,applicationId);
	
	return docUpload;
}


}
