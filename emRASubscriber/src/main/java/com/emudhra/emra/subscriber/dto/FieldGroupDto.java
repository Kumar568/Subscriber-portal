package com.emudhra.emra.subscriber.dto;

import java.util.List;

public class FieldGroupDto {
	
	
	private long id;
	private String name;
	
    private List<MapFieldGroupDto> mapFieldGroupDtoList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MapFieldGroupDto> getMapFieldGroupDtoList() {
		return mapFieldGroupDtoList;
	}

	public void setMapFieldGroupDtoList(List<MapFieldGroupDto> mapFieldGroupDtoList) {
		this.mapFieldGroupDtoList = mapFieldGroupDtoList;
	}
    
    
    
    
    



	
	

}
