package com.emudhra.emra.subscriber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emudhra.emra.subscriber.entity.entity.MapVeritificationChecklist;
import com.emudhra.emra.subscriber.entity.entity.VerificationChecklist;
import com.emudhra.emra.subscriber.repository.MapVerificationChecklistRepository;



@Service
public class MapVerificationChecklistService {

	@Autowired
	private MapVerificationChecklistRepository checklistRepository;
	
	public List<MapVeritificationChecklist> getVerificationChecklist(VerificationChecklist checklist) {
		
		return checklistRepository.findByVerificationChecklistId(checklist);
		
	}
}
