package com.emudhra.emra.subscriber.entity.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.emudhra.emra.subscriber.entity.master.MasPermissions;
import com.emudhra.emra.subscriber.entity.master.UserMaster;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_user_map_permissons")
public class UserMapPermission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_master_id")
	@JsonManagedReference
	private UserMaster userMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_id")
	private MasPermissions masPermissions;

	@Column(name = "is_active")
	private int isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserMaster getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	public MasPermissions getMasPermissions() {
		return masPermissions;
	}

	public void setMasPermissions(MasPermissions masPermissions) {
		this.masPermissions = masPermissions;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		UserMapPermission that = (UserMapPermission) o;
		return Objects.equals(userMaster, that.userMaster) && Objects.equals(masPermissions, that.masPermissions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userMaster, masPermissions);
	}

}
