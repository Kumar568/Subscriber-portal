package com.emudhra.emra.subscriber.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emudhra.emra.subscriber.entity.master.MasProperties;
import com.emudhra.emra.subscriber.repository.MasPropertiesRepository;


@Service
public class MasPropertiesService {
	@Autowired
	private MasPropertiesRepository masPropertiesRepository;
	
	
	public Map<String, String>  getMasPropertiesData(){
	List<MasProperties>  properties = masPropertiesRepository.findAll();
	
	Map<String, String> propertyMap = new HashMap<>();
	for (MasProperties property : properties) {
	    propertyMap.put(property.getName(), property.getValue());
	}
	
	
	
	return propertyMap;
}
	
	
	
	
}
