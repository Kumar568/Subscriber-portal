package com.emudhra.emra.subscriber.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdditionalField {
	
	 @JsonProperty("fieldname")
	    private String fieldName;
	 
	 @JsonProperty("fielddisplayname")
	    private String fieldDisplayName;
	 
	   @JsonProperty("value")
	    private String value;

	    @JsonProperty("ismandatory")
	    private int isMandatory;

	    @JsonProperty("oid")
	    private String oid;

	    @JsonProperty("alphabetsvalidation")
	    private String alphabetsValidation;

	    @JsonProperty("numberscalidation")
	    private String numbersCalidation;

	    @JsonProperty("specialcharactersValidation")
	    private String specialCharactersValidation;
	    
	    @JsonProperty("israisedquery")
	    private String isRaisedQuery;

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public int getIsMandatory() {
			return isMandatory;
		}

		public void setIsMandatory(int isMandatory) {
			this.isMandatory = isMandatory;
		}

		public String getOid() {
			return oid;
		}

		public void setOid(String oid) {
			this.oid = oid;
		}

		public String getAlphabetsValidation() {
			return alphabetsValidation;
		}

		public void setAlphabetsValidation(String alphabetsValidation) {
			this.alphabetsValidation = alphabetsValidation;
		}

		public String getNumbersCalidation() {
			return numbersCalidation;
		}

		public void setNumbersCalidation(String numbersCalidation) {
			this.numbersCalidation = numbersCalidation;
		}

		public String getSpecialCharactersValidation() {
			return specialCharactersValidation;
		}

		public void setSpecialCharactersValidation(String specialCharactersValidation) {
			this.specialCharactersValidation = specialCharactersValidation;
		}

		public String getFieldDisplayName() {
			return fieldDisplayName;
		}

		public void setFieldDisplayName(String fieldDisplayName) {
			this.fieldDisplayName = fieldDisplayName;
		}

		public String getIsRaisedQuery() {
			return isRaisedQuery;
		}

		public void setIsRaisedQuery(String isRaisedQuery) {
			this.isRaisedQuery = isRaisedQuery;
		}

	    

}
