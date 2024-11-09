package com.emudhra.emra.subscriber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emudhra.emra.subscriber.entity.entity.MapVeritificationChecklist;
import com.emudhra.emra.subscriber.entity.entity.VerificationChecklist;



@Repository
public interface MapVerificationChecklistRepository extends JpaRepository<MapVeritificationChecklist, Integer> {

	List<MapVeritificationChecklist> findByVerificationChecklistId(VerificationChecklist checklist);
}
