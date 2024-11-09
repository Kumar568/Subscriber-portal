package com.emudhra.emra.subscriber.entity.master;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import com.emudhra.emra.subscriber.entity.entity.UserMapPermission;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_mas_permissions")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class MasPermissions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "permission_name", nullable = true, length = 200)
	private String permissionName;

	@Column(name = "usertype_id", nullable = false)
	private int userTypeId;

	@Column(name = "isactive", nullable = true)
	private int isActive;
	
	 @OneToMany(
		        mappedBy = "masPermissions",
		        cascade = CascadeType.ALL   
		    )
	 @JsonBackReference
		    private List<UserMapPermission> permissions = new ArrayList();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public int getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public List<UserMapPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<UserMapPermission> permissions) {
		this.permissions = permissions;
	}
	
	

}
