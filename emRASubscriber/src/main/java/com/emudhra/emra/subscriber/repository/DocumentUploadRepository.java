package com.emudhra.emra.subscriber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emudhra.emra.subscriber.entity.entity.DocumentUpload;

@Repository
public interface DocumentUploadRepository extends JpaRepository<DocumentUpload, Long> {

	DocumentUpload findByTypeAndApplicationId(Integer verificationTypeId, Long applicationId);

}
