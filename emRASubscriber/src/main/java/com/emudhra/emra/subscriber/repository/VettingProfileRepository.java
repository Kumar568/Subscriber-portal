package com.emudhra.emra.subscriber.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emudhra.emra.subscriber.entity.entity.ProductMaster;
import com.emudhra.emra.subscriber.entity.entity.VettingProfile;



@Repository
public interface VettingProfileRepository extends JpaRepository<VettingProfile, Integer>{

	Page<VettingProfile> findByIsActive(int isActive, Pageable pageable);
	
	List<VettingProfile> findByIsActive(int isActive);
VettingProfile findBycertificateTemplate(ProductMaster certificateTemplate);

	
	VettingProfile findById(int id);

}
