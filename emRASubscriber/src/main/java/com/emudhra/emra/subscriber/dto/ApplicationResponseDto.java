package com.emudhra.emra.subscriber.dto;

import java.util.List;

import com.emudhra.emra.subscriber.entity.entity.Application;

public class ApplicationResponseDto {
	
	private OrderDetailsDto orderDetailsDto;
	private Application application;
	
	private ApplicationDto applicationDto;
	private FieldGroupDto FieldGroupDto;
	
	
	private List<String> enabledFieldNames;
	private List<String> mandatoryFieldNames;
	
	
	
	
	public List<String> getEnabledFieldNames() {
		return enabledFieldNames;
	}
	public void setEnabledFieldNames(List<String> enabledFieldNames) {
		this.enabledFieldNames = enabledFieldNames;
	}
	public List<String> getMandatoryFieldNames() {
		return mandatoryFieldNames;
	}
	public void setMandatoryFieldNames(List<String> mandatoryFieldNames) {
		this.mandatoryFieldNames = mandatoryFieldNames;
	}
	public FieldGroupDto getFieldGroupDto() {
		return FieldGroupDto;
	}
	public void setFieldGroupDto(FieldGroupDto fieldGroupDto) {
		FieldGroupDto = fieldGroupDto;
	}
	public ApplicationDto getApplicationDto() {
		return applicationDto;
	}
	public void setApplicationDto(ApplicationDto applicationDto) {
		this.applicationDto = applicationDto;
	}
	public OrderDetailsDto getOrderDetailsDto() {
		return orderDetailsDto;
	}
	public void setOrderDetailsDto(OrderDetailsDto orderDetailsDto) {
		this.orderDetailsDto = orderDetailsDto;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	
	

}
