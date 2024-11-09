package com.emudhra.emra.subscriber.dto;

public class FieldValueDTO {
	
	 private String fieldname;
	    private String value;
	    private String oid;
	    private int ismandatory;
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
		public String getOid() {
			return oid;
		}
		public void setOid(String oid) {
			this.oid = oid;
		}
		public int getIsmandatory() {
			return ismandatory;
		}
		public void setIsmandatory(int ismandatory) {
			this.ismandatory = ismandatory;
		}
		

}
