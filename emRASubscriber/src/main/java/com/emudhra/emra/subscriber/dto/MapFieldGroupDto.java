package com.emudhra.emra.subscriber.dto;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MapFieldGroupDto {
	 
	private Long masfieldid;
    private String displayName;
 
    private String fieldName;
    private int isEnabled;
   
    private String value;

    private int isMandatory;
    
	private Integer isDefault;
    

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	  @JsonProperty("alphabetsvalidation")
	    @JsonIgnore
	private Integer alphabetsValidation;
	  @JsonProperty("numberscalidation")
	    @JsonIgnore
	private Integer numbersValidation;
	  @JsonProperty("specialcharactersValidation")
	    @JsonIgnore
	private Integer specialCharactersValidation;
	  @JsonProperty("oid")
	    @JsonIgnore
	private String oid;
	
    

	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public Integer getAlphabetsValidation() {
		return alphabetsValidation;
	}
	public void setAlphabetsValidation(Integer alphabetsValidation) {
		this.alphabetsValidation = alphabetsValidation;
	}
	public Integer getNumbersValidation() {
		return numbersValidation;
	}
	public void setNumbersValidation(Integer numbersValidation) {
		this.numbersValidation = numbersValidation;
	}
	public Integer getSpecialCharactersValidation() {
		return specialCharactersValidation;
	}
	public void setSpecialCharactersValidation(Integer specialCharactersValidation) {
		this.specialCharactersValidation = specialCharactersValidation;
	}
	public Long getMasfieldid() {
		return masfieldid;
	}
	public void setMasfieldid(Long masfieldid) {
		this.masfieldid = masfieldid;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public int getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}
	public int getIsMandatory() {
		return isMandatory;
	}
	public void setIsMandatory(int isMandatory) {
		this.isMandatory = isMandatory;
	}
    
    
}
