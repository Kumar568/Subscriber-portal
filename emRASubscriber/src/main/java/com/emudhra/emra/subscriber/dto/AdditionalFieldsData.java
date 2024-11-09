package com.emudhra.emra.subscriber.dto;

public class AdditionalFieldsData {
	
	 private String fieldname;
	    private String value;
	 
		private int ismandatory;
		private String oid;
	    private String alphabetsvalidation;
	    private String numberscalidation;
	    private String specialcharactersValidation;
	    
	    

		public int getIsmandatory() {
			return ismandatory;
		}
		public void setIsmandatory(int ismandatory) {
			this.ismandatory = ismandatory;
		}
		public String getOid() {
			return oid;
		}
		public void setOid(String oid) {
			this.oid = oid;
		}
	
		public String getFieldname() {
			return fieldname;
		}
		public void setFieldname(String fieldname) {
			this.fieldname = fieldname;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getAlphabetsvalidation() {
			return alphabetsvalidation;
		}
		public void setAlphabetsvalidation(String alphabetsvalidation) {
			this.alphabetsvalidation = alphabetsvalidation;
		}
		public String getNumberscalidation() {
			return numberscalidation;
		}
		public void setNumberscalidation(String numberscalidation) {
			this.numberscalidation = numberscalidation;
		}
		public String getSpecialcharactersValidation() {
			return specialcharactersValidation;
		}
		public void setSpecialcharactersValidation(String specialcharactersValidation) {
			this.specialcharactersValidation = specialcharactersValidation;
		}

	    
	    

}
