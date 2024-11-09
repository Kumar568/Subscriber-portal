package com.emudhra.emra.subscriber.dto;

import org.springframework.web.multipart.MultipartFile;

public class DocumentDTO {

    private   Long containerId; ;
    private MultipartFile file;
	public Long getContainerId() {
		return containerId;
	}
	public void setContainerId(Long containerId) {
		this.containerId = containerId;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
    
}
