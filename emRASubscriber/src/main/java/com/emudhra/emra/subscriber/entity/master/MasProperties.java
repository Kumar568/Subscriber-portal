package com.emudhra.emra.subscriber.entity.master;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.cache.annotation.Cacheable;
@Entity
@Table(name = "tbl_mas_properties")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class MasProperties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "value")
	private String value;
	@Column(name = "is_active")
	private int isactive;
	@Column(name = "createddate")
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@CreationTimestamp
	private LocalDateTime createddate;
	@Column(name = "updateddate")
	private LocalDateTime updateddate;	
	@Column(name = "portal")
	private int portal;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getIsactive() {
		return isactive;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	public LocalDateTime getCreateddate() {
		return createddate;
	}
	public void setCreateddate(LocalDateTime createddate) {
		this.createddate = createddate;
	}
	public LocalDateTime getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(LocalDateTime updateddate) {
		this.updateddate = updateddate;
	}
	public int getPortal() {
		return portal;
	}
	public void setPortal(int portal) {
		this.portal = portal;
	}
	public MasProperties() {
		//super();
		// TODO Auto-generated constructor stub
	}
	public MasProperties(int id, String name, String value, int isactive, LocalDateTime createddate,
			LocalDateTime updateddate, int portal) {
		//super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.isactive = isactive;
		this.createddate = createddate;
		this.updateddate = updateddate;
		this.portal = portal;
	}
	


}
