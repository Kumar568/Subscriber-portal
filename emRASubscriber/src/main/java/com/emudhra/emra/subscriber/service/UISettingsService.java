package com.emudhra.emra.subscriber.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emudhra.emra.subscriber.entity.entity.UISettings;
import com.emudhra.emra.subscriber.enums.Status;
import com.emudhra.emra.subscriber.repository.UISettingsRepository;


@Service
public class UISettingsService {

	@Autowired
	private UISettingsRepository uiSettingsRepository;	
	
	public UISettings getUISettings() {
		 UISettings us = uiSettingsRepository.findById(Status.ACTIVE.getStatusId());
		 return us;
	}
	
}
