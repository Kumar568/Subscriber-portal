package com.emudhra.emra.subscriber.enums;

public enum CertificateDownloadMethodType {
	UPLOADCSR(1, "Upload CSR"), 
	AUTHGENERATECSR(2, "Auto-Generate CSR"),
	CSP(3, "CSP");

	private int id;
	private String name;

	private CertificateDownloadMethodType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
