package com.emudhra.emra.subscriber.entity.master;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Table(name = "tbl_mas_usertype")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class MasUserType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_type", nullable = true, length = 45)
	private String user_type;

	@OneToMany(mappedBy = "masUserType", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<UserMaster> userMaster;
	
	public MasUserType() {
		
	}
	public MasUserType(int id) {
		this.id=id;
	}

	

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public List<UserMaster> getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(List<UserMaster> userMaster) {
		this.userMaster = userMaster;

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
